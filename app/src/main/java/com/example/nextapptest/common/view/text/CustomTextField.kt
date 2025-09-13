package com.example.nextapptest.common.view.text

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nextapptest.common.color.darkGray
import com.example.nextapptest.common.color.purpleGradientColor
import com.example.nextapptest.common.font.robotoRegular

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    value: String,
    hint: String,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = value,
        onValueChange = { newValue ->
            onValueChange.invoke(newValue)
        },
        placeholder = {
            Text(
                hint,
                fontFamily = robotoRegular,
                fontSize = 16.sp,
                color = darkGray
            )
        },
        modifier = modifier
            .clip(shape = CircleShape)
            .fillMaxWidth()
            .border(
                width = 2.dp,
                brush = Brush.linearGradient(
                    colors = purpleGradientColor.reversed()
                ),
                shape = CircleShape
            )
    )
}