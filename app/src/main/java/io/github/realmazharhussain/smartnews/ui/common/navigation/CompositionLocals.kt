package io.github.realmazharhussain.smartnews.ui.common.navigation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.staticCompositionLocalOf

@OptIn(ExperimentalSharedTransitionApi::class)
val LocalSharedTransitionScope = staticCompositionLocalOf<SharedTransitionScope> {
    throw IllegalStateException("Cannot access LocalSharedTransitionScope. Maybe, you forgot to use SmartNewsNavHost?")
}

val LocalAnimatedVisibilityScope = staticCompositionLocalOf<AnimatedVisibilityScope> {
    throw IllegalStateException("Cannot access LocalAnimatedVisibilityScope. Maybe, you forgot to use SmartNewsNavHost?")
}
