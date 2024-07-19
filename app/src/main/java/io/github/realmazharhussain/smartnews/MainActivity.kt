package io.github.realmazharhussain.smartnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import io.github.realmazharhussain.smartnews.ui.SmartNewsApp
import io.github.realmazharhussain.smartnews.ui.theme.SmartNewsTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SmartNewsTheme {
                SmartNewsApp()
            }
        }
    }
}
