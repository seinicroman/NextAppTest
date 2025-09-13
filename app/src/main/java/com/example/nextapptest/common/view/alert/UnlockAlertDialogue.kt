package com.example.nextapptest.common.view.alert

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nextapptest.common.color.purpleGradientColor
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.nextapptest.R
import com.example.nextapptest.common.color.lightGray
import com.example.nextapptest.common.color.lightPurple
import com.example.nextapptest.common.color.purpleMedium
import com.example.nextapptest.common.font.robotoBlack
import com.example.nextapptest.common.font.robotoBold
import com.example.nextapptest.common.modifier.onCondition
import com.example.nextapptest.common.view.button.AppButton
import kotlin.math.abs

@Composable
fun UnlockAlertDialogue(
    modifier: Modifier = Modifier,
    showClose: Boolean,
    isWatching: Boolean,
    onClose: () -> Unit,
    onUnlockClicked: () -> Unit,
    imageId: Int,
) {
    val pulseAnimationTarget = 1.06f
    val infiniteTransition = rememberInfiniteTransition()
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = pulseAnimationTarget,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    val shakingAnimation = remember {
        Animatable(0f)
    }

    val isAtEdge by remember {
        derivedStateOf {
            val nearMin = abs(scale - 1f) < 0.005f
            val nearMax = abs(scale - pulseAnimationTarget) < 0.005f
            nearMin || nearMax
        }
    }

    Dialog(
        onDismissRequest = {
            if (showClose) {
                onClose()
            }
        },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = modifier
                .onCondition(
                    isWatching.not()
                ) {
                    it.scale(1f + shakingAnimation.value)
                }
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .clip(shape = RoundedCornerShape(20.dp))
        ) {
            AlertBackgroundItem(
                modifier = Modifier.matchParentSize()
            )
            AlertContent(
                imageId = imageId,
                isWatching = isWatching,
                onButtonClick = onUnlockClicked,
                scaleButton = scale
            )
            if (showClose) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(10.dp)
                        .size(32.dp)
                        .clickable { onClose.invoke() },
                    contentAlignment = Alignment.Center,
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_close),
                        contentDescription = "close button",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
    LaunchedEffect(isAtEdge) {
        if (isAtEdge) {
            shakingAnimation.animateTo(-0.001f, tween(50))
            shakingAnimation.animateTo(0.001f, tween(100))
            shakingAnimation.animateTo(0f, tween(50))
        }
    }
}

@Composable
fun AlertButton(
    modifier: Modifier = Modifier,
    isWatching: Boolean,
    onButtonClick: () -> Unit
) {


    AppButton(
        modifier = modifier,
        onClick = { onButtonClick.invoke() },
        isLoading = isWatching,
        content = {
            AlertButtonContent(
                modifier = Modifier.fillMaxWidth()
            )
        }
    )
}

@Composable
fun AlertContent(
    modifier: Modifier = Modifier,
    imageId: Int,
    isWatching: Boolean,
    scaleButton: Float,
    onButtonClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(horizontal = 12.dp)
            .padding(top = 50.dp, bottom = 25.dp)
    ) {
        Image(
            painter = painterResource(imageId),
            contentDescription = "keyboard",
            modifier = Modifier
                .clip(shape = RoundedCornerShape(24.dp))
                .fillMaxWidth()
                .height(176.dp)
                .border(width = 4.dp, color = purpleMedium, shape = RoundedCornerShape(24.dp)),
            contentScale = ContentScale.FillBounds
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Watch the ad and get this keyboard",
                style = TextStyle(
                    fontSize = 18.sp,
                    color = lightGray,
                    fontFamily = robotoBold,
                    letterSpacing = 0.sp
                ),
            )
            Text(
                text = "FOR FREE",
                style = TextStyle(
                    fontSize = 20.sp,
                    color = lightPurple,
                    fontFamily = robotoBlack,
                    letterSpacing = 0.sp
                ),
            )
        }
        AlertButton(
            isWatching = isWatching,
            onButtonClick = onButtonClick,
            modifier = Modifier.onCondition(
                isWatching.not()
            ) {
                it.scale(scaleButton)
            }
        )
    }
}

@Composable
fun AlertBackgroundItem(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(Color.White)
            .border(
                width = 2.dp,
                brush = Brush.linearGradient(purpleGradientColor),
                shape = RoundedCornerShape(20.dp)
            ),
    ) {
        Image(
            painter = painterResource(R.drawable.bg_unlock_dialogue),
            contentDescription = "bottom wave",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f),
            contentScale = ContentScale.Crop
        )
    }
}


@Composable
fun AlertButtonContent(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(vertical = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(
            12.dp,
            alignment = Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.ic_play),
            contentDescription = "play icon",
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = "Unlock for free",
            fontSize = 21.sp,
            color = Color.White,
            fontFamily = robotoBold
        )
    }
}