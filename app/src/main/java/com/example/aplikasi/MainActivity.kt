package com.example.aplikasi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.example.aplikasi.ui.AplikasiNavHost
import com.example.aplikasi.ui.theme.AplikasiTheme

/**
 * Single activity. Edge-to-edge is enabled once here so no other screen has to
 * think about insets.
 */
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // Must be called before super.onCreate. On API 31+ it draws the system
        // splash for free; on older versions it emulates it from the theme.
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.getInsetsController(window, window.decorView)
            .isAppearanceLightStatusBars = false

        // Hold the splash only if there is genuinely blocking work. Everything
        // else should render immediately and fill in.
        //
        // splashScreen.setKeepOnScreenCondition { viewModel.isBlocking }

        setContent {
            AplikasiTheme {
                AplikasiNavHost(container = appContainer)
            }
        }
    }
}