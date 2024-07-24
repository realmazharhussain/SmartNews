package io.github.realmazharhussain.smartnews.extension.ui

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.realmazharhussain.smartnews.ui.common.LocalAnimatedContentScope
import io.github.realmazharhussain.smartnews.ui.common.LocalSharedTransitionScope

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun Modifier.sharedElement(key: String) = with(LocalSharedTransitionScope.current) {
    sharedElement(rememberSharedContentState(key = key), LocalAnimatedContentScope.current)
}