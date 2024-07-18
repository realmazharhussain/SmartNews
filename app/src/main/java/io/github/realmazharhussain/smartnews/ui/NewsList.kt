package io.github.realmazharhussain.smartnews.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.realmazharhussain.smartnews.extension.repeat
import io.github.realmazharhussain.smartnews.network.dto.Article
import io.github.realmazharhussain.smartnews.ui.theme.SmartNewsTheme

@Composable
fun NewsList(articles: List<Article>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier.fillMaxWidth(),
        contentPadding = PaddingValues(vertical = 4.dp)
    ) {
        items(articles) {
            NewsItem(it)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NewsListPreview() {
    SmartNewsTheme {
        NewsList(Article.mock().repeat(20))
    }
}