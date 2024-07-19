package io.github.realmazharhussain.smartnews.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import io.github.realmazharhussain.smartnews.extension.ui.loadState

@Composable
fun <T: Any> LazyItemsColumn(
    items: LazyPagingItems<T>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    itemContent: @Composable (T) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        loadState(items.loadState.prepend)
        loadState(items.loadState.refresh) { items(items.itemCount) { itemContent(items[it]!!) } }
        loadState(items.loadState.append)
    }
}
