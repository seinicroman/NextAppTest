package com.example.nextapptest.common.view.loading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.nextapptest.common.color.lightPurple

@Composable
fun FullLoadingScreen(){
    Box(modifier = Modifier.fillMaxSize().background(Color.White), contentAlignment = Alignment.Center){
        CircularProgressIndicator(
            color = lightPurple,
            strokeWidth = 2.dp,
            modifier = Modifier.size(32.dp)
        )
    }
}