package io.github.realmazharhussain.smartnews.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import io.github.realmazharhussain.smartnews.R
import io.github.realmazharhussain.smartnews.data.network.dto.Article
import io.github.realmazharhussain.smartnews.extension.repeat
import io.github.realmazharhussain.smartnews.ui.common.SmartNewsTopBar
import io.github.realmazharhussain.smartnews.ui.theme.SmartNewsTheme
import kotlinx.coroutines.flow.flowOf

@Composable
fun HomeScreen(
    onArticleClicked: (Article) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: NewsViewModel = hiltViewModel()
) {
    HomeScreenContent(
        items = viewModel.everything.collectAsLazyPagingItems(),
        onArticleClicked = onArticleClicked,
        modifier = modifier
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeScreenContent(
    items: LazyPagingItems<Article>,
    onArticleClicked: (Article) -> Unit,
    modifier: Modifier = Modifier,
) {
    val pullToRefreshState = rememberPullToRefreshState()
    val isRefreshing by remember { derivedStateOf { items.loadState.refresh is LoadState.Loading } }

    if (pullToRefreshState.isRefreshing) {
        LaunchedEffect(Unit) {
            items.refresh()
        }
    }

    LaunchedEffect(isRefreshing) {
        if (isRefreshing) {
            pullToRefreshState.startRefresh()
        } else {
            pullToRefreshState.endRefresh()
        }
    }

    Box(
        modifier = modifier.nestedScroll(pullToRefreshState.nestedScrollConnection),
    ) {

        val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

        Scaffold(
            modifier = modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                SmartNewsTopBar(
                    stringResource(R.string.news),
                    scrollBehavior = scrollBehavior,
                    actions = {
                        IconButton(onClick = { items.refresh() }) {
                            Icon(
                                painter = rememberVectorPainter(Icons.Default.Refresh),
                                contentDescription = stringResource(R.string.refresh)
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->

            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            ) {

                when (val refreshState = items.loadState.refresh) {
                    is LoadState.Error -> ErrorIndicator(error = refreshState.error, Modifier.align(Alignment.Center))
                    else -> {
                        LazyColumn(
                            contentPadding = PaddingValues(vertical = 4.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = modifier.fillMaxSize(),
                        ) {
                            items(items.itemCount) {
                                val article = items[it]!!
                                NewsItem(article, onClick = { onArticleClicked(article) })
                            }

                            if (!isRefreshing) when (val appendState = items.loadState.append) {
                                is LoadState.NotLoading -> {}
                                is LoadState.Loading -> item { CircularProgressIndicator() }
                                is LoadState.Error -> item { ErrorIndicator(appendState.error) }
                            }
                        }
                    }
                }

                PullToRefreshContainer(pullToRefreshState, Modifier.align(Alignment.TopCenter))
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    SmartNewsTheme {
        HomeScreenContent(flowOf(PagingData.from(Article.mock().repeat(20))).collectAsLazyPagingItems(), onArticleClicked = {})
    }
}

@Composable
fun ErrorIndicator(error: Throwable, modifier: Modifier = Modifier) {
    Text(text = "Error Occurred: $error", modifier)
}
