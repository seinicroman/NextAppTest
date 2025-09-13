package com.example.nextapptest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.nextapptest.screen.MyKeyboardScreen
import com.example.nextapptest.ui.theme.NextAppTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NextAppTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyKeyboardScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}