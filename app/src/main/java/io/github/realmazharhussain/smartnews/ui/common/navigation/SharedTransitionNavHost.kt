package io.github.realmazharhussain.smartnews.ui.common.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
@OptIn(ExperimentalSharedTransitionApi::class)
fun SharedTransitionNavHost(
    navController: NavHostController,
    startDestination: Any,
    modifier: Modifier = Modifier,
    builder: NavGraphBuilder.() -> Unit
) {
    SharedTransitionLayout {
        CompositionLocalProvider(LocalSharedTransitionScope provides this) {
            NavHost(navController, startDestination, modifier) {
                builder()
            }
        }
    }
}
