package io.github.realmazharhussain.smartnews.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Screen(title: String, modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { SmartNewsTopBar(title) }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) { content() }
    }
}
