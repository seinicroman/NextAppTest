package com.example.nextapptest.common.view.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nextapptest.R
import com.example.nextapptest.common.color.purpleGradientColor
import com.example.nextapptest.common.font.robotoMedium


@Composable
fun UnlockItem(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(
                brush = Brush.linearGradient(
                    colors = purpleGradientColor.reversed()
                ),
                shape = RoundedCornerShape(
                    bottomStart = 14.dp,
                    topEnd = 14.dp
                )
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        UnlockItemContent()
    }
}

@Composable
fun UnlockItemContent(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(
            horizontal = 10.dp,
        ),
        horizontalArrangement = Arrangement.spacedBy(4.5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.ic_gift),
            contentDescription = "",
            modifier = Modifier.size(14.dp)
        )
        Text(
            text = "Unlock",
            fontFamily = robotoMedium,
            fontSize = 12.sp,
            color = Color.White,
            lineHeight = 23.sp
        )
    }
}