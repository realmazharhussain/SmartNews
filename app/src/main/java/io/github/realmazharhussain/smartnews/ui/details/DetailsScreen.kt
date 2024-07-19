package io.github.realmazharhussain.smartnews.ui.details

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.realmazharhussain.smartnews.network.dto.Article
import io.github.realmazharhussain.smartnews.ui.theme.SmartNewsTheme

@Composable
fun ArticleDetails(article: Article, modifier: Modifier = Modifier) {
    Text(article.title, modifier)
}

@Preview
@Composable
private fun ArticleDetailsPreview() {
    SmartNewsTheme {
        ArticleDetails(Article.mock())
    }
}
