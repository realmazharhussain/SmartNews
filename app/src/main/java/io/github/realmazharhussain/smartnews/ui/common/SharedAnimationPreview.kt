package io.github.realmazharhussain.smartnews.ui.common

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import io.github.realmazharhussain.smartnews.ui.theme.SmartNewsTheme

@ExperimentalSharedTransitionApi
@Composable
fun SharedAnimationPreview(content: @Composable () -> Unit) {
    SmartNewsTheme {
        SharedTransitionLayout {
            CompositionLocalProvider(LocalSharedTransitionScope provides this) {
                AnimatedContent(targetState = 1.0, label = "") {
                    CompositionLocalProvider(LocalAnimatedContentScope provides this) {
                        content()
                    }
                    it.let {}
                }
            }
        }
    }
}
