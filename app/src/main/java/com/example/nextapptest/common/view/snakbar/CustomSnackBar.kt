package com.example.nextapptest.common.view.snakbar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.nextapptest.common.color.purpleGradientColor

@Composable
fun CustomSnackBar(
    data : SnackbarData
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(CircleShape)
            .background(Color.White)
            .border(
                width = 2.dp,
                brush = Brush.linearGradient(
                    colors = purpleGradientColor.reversed()
                ),
                shape = CircleShape
            )
            .padding(horizontal = 20.dp, vertical = 12.dp)
    ) {
        Text(
            text = data.visuals.message,
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterStart)
        )
    }
}