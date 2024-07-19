package io.github.realmazharhussain.smartnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.paging.compose.collectAsLazyPagingItems
import dagger.hilt.android.AndroidEntryPoint
import io.github.realmazharhussain.smartnews.ui.home.HomeScreen
import io.github.realmazharhussain.smartnews.ui.home.NewsViewModel
import io.github.realmazharhussain.smartnews.ui.theme.SmartNewsTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val newsViewModel: NewsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SmartNewsTheme {
                HomeScreen(newsViewModel.everything.collectAsLazyPagingItems())
            }
        }
    }
}
