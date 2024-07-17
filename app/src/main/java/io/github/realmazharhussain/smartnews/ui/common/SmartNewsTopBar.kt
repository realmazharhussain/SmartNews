package io.github.realmazharhussain.smartnews.ui.common

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.graphics.ColorUtils
import io.github.realmazharhussain.smartnews.R
import io.github.realmazharhussain.smartnews.ui.theme.SmartNewsTheme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SmartNewsTopBar(
    title: String,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    scrollBehavior: TopAppBarScrollBehavior? = null,
) {
    TopAppBar(
        title = { Text(text = title) },
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        navigationIcon = navigationIcon,
        actions = actions,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(ColorUtils.blendARGB(
                MaterialTheme.colorScheme.primaryContainer.toArgb(),
                MaterialTheme.colorScheme.surfaceContainer.toArgb(),
                0.95f
            ))
        ),
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
