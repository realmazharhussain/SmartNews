package io.github.realmazharhussain.smartnews.extension.ui

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.realmazharhussain.smartnews.ui.common.navigation.LocalAnimatedVisibilityScope
import io.github.realmazharhussain.smartnews.ui.common.navigation.LocalSharedTransitionScope

@Composable
@OptIn(ExperimentalSharedTransitionApi::class)
fun Modifier.sharedElement(key: String) = with(LocalSharedTransitionScope.current) {
    sharedElement(rememberSharedContentState(key = key), LocalAnimatedVisibilityScope.current)
}