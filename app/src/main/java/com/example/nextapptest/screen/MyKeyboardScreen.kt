package com.example.nextapptest.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.nextapptest.R
import com.example.nextapptest.common.add.AddBannerItem
import com.example.nextapptest.common.color.backgroundColor
import com.example.nextapptest.common.color.headerBackgroundColor
import com.example.nextapptest.common.view.alert.UnlockAlertDialogue
import com.example.nextapptest.common.view.common.UnlockItem
import com.example.nextapptest.common.view.loading.FullLoadingScreen
import com.example.nextapptest.common.view.snakbar.CustomSnackBar
import com.example.nextapptest.common.view.text.CustomTextField
import com.example.nextapptest.common.view.text.GradientTextExample

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyKeyboardScreen(
    modifier: Modifier = Modifier,
    viewModel: MyKeyboardViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    if (uiState.isLoading) {
        FullLoadingScreen()
    } else {
        MyKeyboardScreenContent(
            themes = uiState.themes,
            searchText = uiState.searchText,
            handleEvent = viewModel::handleEvent,
            snackBarMessage = uiState.snackbarMessage,
        )
    }

    if (uiState.isRewardPopupVisible && uiState.selectedRewardThemeId != null) {
        RewardedDialog(
            showClose = uiState.rewardPopupCloseButtonVisible,
            isWatching = uiState.isWatchingAd,
            onClose = { viewModel.handleEvent(MyKeyboardEvent.DismissRewardPopup) },
            onUnlockClicked = { viewModel.handleEvent(MyKeyboardEvent.UnlockRewardedThemeClicked) },
            imageId = uiState.themes.firstOrNull { it.id == uiState.selectedRewardThemeId }?.imageId
                ?: R.drawable.ic_test_keyboard_1
        )
    }
}

@Composable
fun MyKeyboardScreenContent(
    themes: List<KeyboardTheme>,
    searchText: String,
    handleEvent: (MyKeyboardEvent) -> Unit,
    snackBarMessage: String?,
) {
    val snackBarHostState = remember { SnackbarHostState() }

    Scaffold(
        containerColor = backgroundColor,
        topBar = {
            MyKeyboardScreenHeader(
                textFieldValue = searchText,
                onValueChange = {
                    handleEvent(MyKeyboardEvent.SearchTextChanged(it))
                }
            )
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState,
                snackbar = { data ->
                    CustomSnackBar(data)
                }
            )
        },
        content = { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    items(themes, key = { it.id }) { theme ->
                        ThemeItem(
                            theme = theme,
                            onClick = { handleEvent(MyKeyboardEvent.ThemeClicked(theme.id)) },
                        )
                    }
                }
                AddBannerItem(modifier = Modifier)
            }
        }
    )

    LaunchedEffect(snackBarMessage) {
        snackBarMessage?.let { msg ->
            launch {
                snackBarHostState.showSnackbar(msg)
                handleEvent(MyKeyboardEvent.ClearSnackbar())
            }
        }
    }
}

@Composable
private fun ThemeItem(theme: KeyboardTheme, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clickable(onClick = onClick)
            .clip(shape = RoundedCornerShape(14.dp)),
    ) {
        Image(
            painter = painterResource(theme.imageId),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp),
            contentScale = ContentScale.FillBounds
        )
        if (theme.isLocked) {
            UnlockItem(
                modifier = Modifier
                    .align(Alignment.TopEnd)
            )
        }
    }
}

@Composable
private fun RewardedDialog(
    showClose: Boolean,
    isWatching: Boolean,
    onClose: () -> Unit,
    onUnlockClicked: () -> Unit,
    imageId: Int
) {
    UnlockAlertDialogue(
        imageId = imageId,
        showClose = showClose,
        isWatching = isWatching,
        onClose = onClose,
        onUnlockClicked = onUnlockClicked
    )
}

@Composable
fun MyKeyboardScreenHeader(
    modifier: Modifier = Modifier,
    textFieldValue: String,
    onValueChange: (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .background(
                headerBackgroundColor,
                shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
            )
            .padding(
                top = 30.dp,
                bottom = 20.dp
            )
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        GradientTextExample()
        CustomTextField(
            value = textFieldValue,
            hint = "Try your keyboard...",
            onValueChange = onValueChange
        )
    }
}
