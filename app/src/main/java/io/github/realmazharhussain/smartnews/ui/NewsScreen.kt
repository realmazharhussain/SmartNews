package io.github.realmazharhussain.smartnews.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.realmazharhussain.smartnews.common.Result
import io.github.realmazharhussain.smartnews.common.TaskState
import io.github.realmazharhussain.smartnews.extension.repeat
import io.github.realmazharhussain.smartnews.network.dto.Article
import io.github.realmazharhussain.smartnews.network.dto.NewsRsp
import io.github.realmazharhussain.smartnews.ui.theme.SmartNewsTheme

@Composable
fun NewsScreen(state: TaskState<NewsRsp>, modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { SmartNewsTopBar() }
    ) { innerPadding ->
        Box(
            Modifier
                .padding(innerPadding)
                .fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            when (state) {
                is TaskState.Ongoing -> CircularProgressIndicator()
                is TaskState.Completed -> when (state.result) {
                    is Result.Failure -> Text(text = "Error Occurred: ${state.result.cause}")
                    is Result.Success -> when (state.result.data) {
                        is NewsRsp.Error -> Text(text = "Error Occurred: ${state.result.data.message}")
                        is NewsRsp.Ok -> NewsList(state.result.data.articles.filter { it.title != "[Removed]" })
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun NewsScreenPreview() {
    SmartNewsTheme {
        NewsScreen(TaskState.Completed(Result.Success(NewsRsp.Ok(50, Article.mock().repeat(20)))))
    }
}
