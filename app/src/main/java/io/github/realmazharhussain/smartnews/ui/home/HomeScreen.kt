package io.github.realmazharhussain.smartnews.ui.home

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import io.github.realmazharhussain.smartnews.R
import io.github.realmazharhussain.smartnews.data.network.dto.Article
import io.github.realmazharhussain.smartnews.extension.repeat
import io.github.realmazharhussain.smartnews.ui.common.SharedAnimationPreview
import io.github.realmazharhussain.smartnews.ui.common.SmartNewsTopBar
import kotlinx.coroutines.flow.flowOf

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeScreen(
    items: LazyPagingItems<Article>,
    onArticleClicked: (Article) -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { SmartNewsTopBar(stringResource(R.string.news), scrollBehavior = scrollBehavior) }
    ) { innerPadding ->
        NewsList(items, onArticleClicked, modifier = Modifier.padding(innerPadding))
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    @OptIn(ExperimentalSharedTransitionApi::class)
    SharedAnimationPreview {
        HomeScreen(flowOf(PagingData.from(Article.mock().repeat(20))).collectAsLazyPagingItems(), onArticleClicked = {})
    }
}
