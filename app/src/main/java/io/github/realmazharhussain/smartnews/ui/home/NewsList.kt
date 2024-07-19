package io.github.realmazharhussain.smartnews.ui.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import io.github.realmazharhussain.smartnews.extension.repeat
import io.github.realmazharhussain.smartnews.data.network.dto.Article
import io.github.realmazharhussain.smartnews.ui.common.LazyItemsColumn
import io.github.realmazharhussain.smartnews.ui.theme.SmartNewsTheme
import kotlinx.coroutines.flow.flowOf

@Composable
fun NewsList(items: LazyPagingItems<Article>, onArticleClicked: (Article) -> Unit, modifier: Modifier = Modifier) {
    LazyItemsColumn(
        items = items,
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 4.dp),
        itemContent = { NewsItem(it, onClick = { onArticleClicked(it) }) },
    )
}

@Preview(showBackground = true)
@Composable
private fun NewsListPreview() {
    SmartNewsTheme {
        NewsList(flowOf(PagingData.from(Article.mock().repeat(20))).collectAsLazyPagingItems(), onArticleClicked = {})
    }
}
