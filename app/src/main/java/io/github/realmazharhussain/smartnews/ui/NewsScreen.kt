package io.github.realmazharhussain.smartnews.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import io.github.realmazharhussain.smartnews.extension.repeat
import io.github.realmazharhussain.smartnews.network.dto.Article
import io.github.realmazharhussain.smartnews.ui.theme.SmartNewsTheme
import kotlinx.coroutines.flow.flowOf

@Composable
fun NewsScreen(items: LazyPagingItems<Article>, modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { SmartNewsTopBar() }
    ) { innerPadding ->
        NewsList(items, Modifier.padding(innerPadding))
    }
}

@Preview
@Composable
private fun NewsScreenPreview() {
    SmartNewsTheme {
        NewsScreen(flowOf(PagingData.from(Article.mock().repeat(20))).collectAsLazyPagingItems())
    }
}
