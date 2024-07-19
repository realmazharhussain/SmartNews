package io.github.realmazharhussain.smartnews.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import io.github.realmazharhussain.smartnews.R
import io.github.realmazharhussain.smartnews.ui.theme.SmartNewsTheme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SmartNewsTopBar(modifier: Modifier = Modifier) {
    TopAppBar(
        title = { Text(text = stringResource(R.string.news)) },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        modifier = modifier
    )
}

@Preview
@Composable
private fun SmartNewsTopBarPreview() {
    SmartNewsTheme {
        SmartNewsTopBar()
    }
}
