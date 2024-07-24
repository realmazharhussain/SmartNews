package io.github.realmazharhussain.smartnews.ui.common

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.staticCompositionLocalOf

@OptIn(ExperimentalSharedTransitionApi::class)
val LocalSharedTransitionScope = staticCompositionLocalOf {
    @Suppress("CAST_NEVER_SUCCEEDS")
    null as SharedTransitionScope
}

val LocalAnimatedContentScope = staticCompositionLocalOf {
    @Suppress("CAST_NEVER_SUCCEEDS")
    null as AnimatedContentScope
}
