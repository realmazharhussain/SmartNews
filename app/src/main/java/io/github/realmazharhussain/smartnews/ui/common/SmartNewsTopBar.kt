package io.github.realmazharhussain.smartnews.ui.common

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import io.github.realmazharhussain.smartnews.R
import io.github.realmazharhussain.smartnews.ui.theme.SmartNewsTheme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SmartNewsTopBar(
    title: String,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
) {
    TopAppBar(
        title = { Text(text = title) },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        modifier = modifier,
        scrollBehavior = scrollBehavior,
    )
}

@Preview
@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun SmartNewsTopBarPreview() {
    SmartNewsTheme {
        SmartNewsTopBar(stringResource(id = R.string.app_name))
    }
}
