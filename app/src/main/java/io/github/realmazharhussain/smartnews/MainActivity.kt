package io.github.realmazharhussain.smartnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import dagger.hilt.android.AndroidEntryPoint
import io.github.realmazharhussain.smartnews.common.TaskState
import io.github.realmazharhussain.smartnews.ui.NewsScreen
import io.github.realmazharhussain.smartnews.ui.NewsViewModel
import io.github.realmazharhussain.smartnews.ui.theme.SmartNewsTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val newsViewModel: NewsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SmartNewsTheme {
                val state by newsViewModel.everything.collectAsState(TaskState.Ongoing())
                NewsScreen(state)
            }
        }
    }
}
