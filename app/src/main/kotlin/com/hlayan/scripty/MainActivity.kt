package com.hlayan.scripty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.hlayan.scripty.ui.home.HomeScreen
import com.hlayan.scripty.ui.theme.ScriptyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            ScriptyTheme {
                val systemUiController = rememberSystemUiController()
                systemUiController.setStatusBarColor(Color.White)
                HomeScreen()
            }
        }
    }
}