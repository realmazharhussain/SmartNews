package io.github.realmazharhussain.smartnews.ui.common.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
@OptIn(ExperimentalSharedTransitionApi::class)
fun SharedTransitionNavHost(
    startDestination: Any,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    builder: SharedTransitionNavGraphBuilder.() -> Unit
) {
    SharedTransitionLayout {
        CompositionLocalProvider(LocalSharedTransitionScope provides this) {
            NavHost(navController, startDestination, modifier) {
                SharedTransitionNavGraphBuilder(this, navController).builder()
            }
        }
    }
}

class SharedTransitionNavGraphBuilder(
    val builder: NavGraphBuilder,
    val navController: NavHostController
)

