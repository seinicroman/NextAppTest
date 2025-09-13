package com.example.nextapptest.common.view.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.nextapptest.common.color.buttonShadowColor
import com.example.nextapptest.common.color.purpleGradientColor
import com.example.nextapptest.common.modifier.onCondition

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    content: @Composable () -> Unit
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }

    val isClicked = interactionSource.collectIsPressedAsState()

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    color = buttonShadowColor,
                    shape = CircleShape,
                )
                .clickable(
                    enabled = enabled,
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    onClick.invoke()
                }
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .padding(
                    bottom = if (isClicked.value) {
                        2.dp
                    } else {
                        4.5.dp
                    }
                )
                .background(
                    brush = Brush.linearGradient(
                        colors = purpleGradientColor,
                    ),
                    shape = CircleShape,
                )
        )
        Box(
            modifier = Modifier
                .defaultMinSize(minHeight = 30.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .onCondition(isLoading) { it.alpha(0f) },
            ) {
                content()
            }
            if (isLoading) {
                CircularProgressIndicator(
                    color = Color.White,
                    strokeWidth = 2.dp,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}