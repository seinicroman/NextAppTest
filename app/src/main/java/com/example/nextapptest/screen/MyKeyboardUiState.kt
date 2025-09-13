package com.example.nextapptest.screen

import androidx.compose.runtime.Stable

data class KeyboardTheme(
    val id : Int,
    val isLocked: Boolean,
    val wasSelected : Boolean,
    val imageId : Int
)

@Stable
data class MyKeyboardUiState(
    val searchText: String = "",
    val themes: List<KeyboardTheme> = emptyList(),
    val isRewardPopupVisible: Boolean = false,
    val selectedRewardThemeId: Int? = null,
    val rewardPopupCloseButtonVisible: Boolean = false,
    val isWatchingAd: Boolean = false,
    val snackbarMessage: String? = null,

    val isLoading : Boolean = false
)

sealed interface MyKeyboardEvent {
    data class SearchTextChanged(val text: String): MyKeyboardEvent
    data class ThemeClicked(val themeId: Int): MyKeyboardEvent
    object DismissRewardPopup: MyKeyboardEvent
    object UnlockRewardedThemeClicked: MyKeyboardEvent
    data class ClearSnackbar(val id: Int = 0): MyKeyboardEvent
}