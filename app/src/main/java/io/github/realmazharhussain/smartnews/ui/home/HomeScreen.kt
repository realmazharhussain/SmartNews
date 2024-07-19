package io.github.realmazharhussain.smartnews.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import io.github.realmazharhussain.smartnews.R
import io.github.realmazharhussain.smartnews.data.network.dto.Article
import io.github.realmazharhussain.smartnews.extension.repeat
import io.github.realmazharhussain.smartnews.ui.common.Screen
import io.github.realmazharhussain.smartnews.ui.theme.SmartNewsTheme
import kotlinx.coroutines.flow.flowOf

@Composable
fun HomeScreen(
    items: LazyPagingItems<Article>,
    onArticleClicked: (Article) -> Unit,
    modifier: Modifier = Modifier,
) {
    Screen(title = stringResource(R.string.news), modifier) {
        NewsList(items, onArticleClicked)
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    SmartNewsTheme {
        HomeScreen(
            items = flowOf(PagingData.from(Article.mock().repeat(20))).collectAsLazyPagingItems(),
            onArticleClicked = {}
        )
    }
}
