package com.example.nextapptest.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nextapptest.core.Status
import com.example.nextapptest.domain.model.ThemeModel
import com.example.nextapptest.domain.usecase.GetAllThemesFromDbUseCase
import com.example.nextapptest.domain.usecase.InitializeDataBaseThemesIfRequiredUseCase
import com.example.nextapptest.domain.usecase.UpdateThemeInDbUseCase
import com.example.nextapptest.domain.usecase.WasThemesAlreadyAddedToDbUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyKeyboardViewModel @Inject constructor(
    private val updateThemeInDbUseCase: UpdateThemeInDbUseCase,
    private val getAllThemesFromDbUseCase: GetAllThemesFromDbUseCase,
    private val initializeDataBaseThemesIfRequiredUseCase: InitializeDataBaseThemesIfRequiredUseCase,
    private val wasThemesAlreadyAddedToDbUseCase: WasThemesAlreadyAddedToDbUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(MyKeyboardUiState())
    val uiState = _uiState.asStateFlow()

    private var closeButtonTimerJob: Job? = null

    init {
        initializeThemesScreen()
    }

    fun handleEvent(event: MyKeyboardEvent) {
        when (event) {
            is MyKeyboardEvent.SearchTextChanged -> {
                _uiState.update { it.copy(searchText = event.text) }
            }

            is MyKeyboardEvent.ThemeClicked -> {
                onThemeClicked(event.themeId)
            }

            MyKeyboardEvent.DismissRewardPopup -> {
                _uiState.update {
                    it.copy(
                        isRewardPopupVisible = false,
                        selectedRewardThemeId = null,
                        rewardPopupCloseButtonVisible = false
                    )
                }
                closeButtonTimerJob?.cancel()
            }

            MyKeyboardEvent.UnlockRewardedThemeClicked -> {
                startAdSimulationAndUnlock()
            }

            is MyKeyboardEvent.ClearSnackbar -> {
                _uiState.update { it.copy(snackbarMessage = null) }
            }
        }
    }

    private fun initializeThemesScreen() {
        viewModelScope.launch {
            val wasInitialized = wasThemesAlreadyAddedToDbUseCase()
            if (wasInitialized) {
                getAllThemes()
            } else {
                changeLoadingState(true)
                initializeDataBaseThemesIfRequiredUseCase().let {
                    when (it) {
                        is Status.Error -> {
                            handleOnInitializeThemesInDbError()
                        }

                        is Status.Success -> {
                            handleOnInitializeThemesInDbSuccess()
                        }
                    }
                }
                changeLoadingState(false)
            }
        }
    }

    private fun handleOnInitializeThemesInDbSuccess() {
        getAllThemes()
    }

    private fun handleOnInitializeThemesInDbError() {
    }

    private fun getAllThemes() {
        changeLoadingState(true)
        viewModelScope.launch {
            getAllThemesFromDbUseCase().let { result ->
                when (result) {
                    is Status.Error -> {
                        onGetAllThemesError()
                    }

                    is Status.Success -> {
                        handleOnnGetAllThemesSuccess(result.data)
                        changeLoadingState(false)
                    }
                }
            }
        }
    }

    private fun handleOnnGetAllThemesSuccess(list: List<ThemeModel>) {
        val themeList = list.sortedBy { it.id }.map {
            KeyboardTheme(
                id = it.id,
                isLocked = it.wasUnlocked.not(),
                wasSelected = it.isSelected,
                imageId = it.imageId
            )
        }
        _uiState.update { state ->
            state.copy(
                themes = themeList
            )
        }
    }

    private fun onGetAllThemesError() {
    }

    private fun updateTheme(themeId: Int) {
        viewModelScope.launch {
            uiState.value.themes.firstOrNull { th -> th.id == themeId }?.let { theme ->
                updateThemeInDbUseCase(
                    ThemeModel(
                        id = theme.id,
                        imageId = theme.imageId,
                        isSelected = theme.wasSelected,
                        wasUnlocked = true
                    )
                ).let { result ->
                    when (result) {
                        is Status.Error -> {
                            handleOnUpdateThemeError()
                        }

                        is Status.Success -> {
                            handleOnUpdateThemeSuccess(themeId)
                        }
                    }
                }
            }
        }
    }

    private fun handleOnUpdateThemeSuccess(id: Int) {
        val newThemes = _uiState.value.themes.map {
            if (it.id == id) it.copy(isLocked = false) else it
        }

        _uiState.update { state ->
            state.copy(
                themes = newThemes,
                isWatchingAd = false,
                isRewardPopupVisible = false,
                selectedRewardThemeId = null,
                rewardPopupCloseButtonVisible = false,
                snackbarMessage = "Theme unlocked!"
            )
        }
    }

    private fun handleOnUpdateThemeError() {
    }

    private fun onThemeClicked(themeId: Int) {
        val theme = _uiState.value.themes.find { it.id == themeId } ?: return
        if (theme.isLocked) {
            _uiState.update {
                it.copy(
                    isRewardPopupVisible = true,
                    selectedRewardThemeId = themeId,
                    rewardPopupCloseButtonVisible = false
                )
            }
            closeButtonTimerJob?.cancel()
            closeButtonTimerJob = viewModelScope.launch {
                delay(5_000)
                _uiState.update { st -> st.copy(rewardPopupCloseButtonVisible = true) }
            }
        } else {
            _uiState.update { st -> st.copy(snackbarMessage = "Theme applied!") }
        }
    }

    private fun startAdSimulationAndUnlock() {
        val themeId = _uiState.value.selectedRewardThemeId ?: return
        if (_uiState.value.isWatchingAd) return

        viewModelScope.launch {
            _uiState.update { it.copy(isWatchingAd = true) }
            val waitMs = 5000
            delay(waitMs.toLong())

            updateTheme(themeId)

            closeButtonTimerJob?.cancel()
        }
    }

    private fun changeLoadingState(loading: Boolean) {
        _uiState.update {
            it.copy(
                isLoading = loading
            )
        }
    }
}