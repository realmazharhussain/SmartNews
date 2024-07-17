package io.github.realmazharhussain.smartnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.realmazharhussain.smartnews.ui.common.NewsItem
import io.github.realmazharhussain.smartnews.ui.theme.SmartNewsTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SmartNewsTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = "News")
                            },
                            colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                        )
                    }
                ) { innerPadding ->
                    LazyColumn(
                        Modifier
                            .fillMaxWidth()
                            .padding(innerPadding),
                        contentPadding = PaddingValues(vertical = 4.dp)
                    ) {
                        items(100) {
                            NewsItem()
                        }
                    }
                }
            }
        }
    }
}