package com.example.nextapptest.common.add

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.nextapptest.R
import com.example.nextapptest.common.color.bottomBarColor

@Composable
fun AddBannerItem(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(62.dp)
            .background(bottomBarColor)
    ) {
        Image(
            painter = painterResource(R.drawable.ic_add_banner),
            contentDescription = "google add",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize().padding(vertical = 6.dp, horizontal = 30.dp)
        )
    }
}