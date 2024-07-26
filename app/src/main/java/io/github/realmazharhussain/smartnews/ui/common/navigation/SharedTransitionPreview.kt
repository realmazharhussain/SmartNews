package io.github.realmazharhussain.smartnews.ui.common.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import io.github.realmazharhussain.smartnews.ui.theme.SmartNewsTheme

@Composable
@OptIn(ExperimentalSharedTransitionApi::class)
fun SharedTransitionPreview(content: @Composable () -> Unit) {
    SmartNewsTheme {
        SharedTransitionLayout {
            AnimatedVisibility(visible = true) {
                CompositionLocalProvider(
                    LocalSharedTransitionScope provides this@SharedTransitionLayout,
                    LocalAnimatedVisibilityScope provides this@AnimatedVisibility,
                ) {
                    content()
                }
            }
        }
    }
}
