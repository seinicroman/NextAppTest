package com.example.nextapptest.common.view.text

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nextapptest.common.color.headerBackgroundColor
import com.example.nextapptest.common.color.purpleGradientColor
import com.example.nextapptest.common.color.tittleShadowColor
import com.example.nextapptest.common.font.titanOneRegular

@Composable
fun GradientTextExample() {
    Box(
        modifier = Modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "MY KEYBOARD",
            style = TextStyle(
                fontSize = 36.sp,
                color = tittleShadowColor,
                fontFamily = titanOneRegular,
            ),
            modifier = Modifier.offset(
                x = (-2.5).dp,
                y = 2.5.dp
            )
        )
        Text(
            text = "MY KEYBOARD",
            style = TextStyle(
                fontSize = 36.sp,
                color = headerBackgroundColor,
                fontFamily = titanOneRegular,
            ),
            modifier = Modifier
                .offset(
                    x = (-1.5).dp,
                    y =1.5.dp
                )
        )

        Text(
            text = "MY KEYBOARD",
            style = TextStyle(
                fontSize = 36.sp,
                brush = Brush.linearGradient(
                    colors = purpleGradientColor.reversed(),
                ),
                fontFamily = titanOneRegular
            ),
            modifier = Modifier
        )
    }
}