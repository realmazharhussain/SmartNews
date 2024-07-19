package io.github.realmazharhussain.smartnews.extension.ui

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.paging.LoadState

fun LazyListScope.loadState(
    state: LoadState,
    loadingIndicator: @Composable () -> Unit = @Composable { CircularProgressIndicator() },
    errorIndicator: @Composable (error: Throwable) -> Unit = @Composable { Text(text = "Error Occurred: $it") },
    itemAdapter: LazyListScope.() -> Unit = {}
) {
    when (state) {
        is LoadState.NotLoading -> itemAdapter()
        is LoadState.Loading -> item { loadingIndicator() }
        is LoadState.Error -> item { errorIndicator(state.error) }
    }
}