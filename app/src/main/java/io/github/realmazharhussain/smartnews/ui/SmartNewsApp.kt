package io.github.realmazharhussain.smartnews.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import io.github.realmazharhussain.smartnews.ui.common.navigation.LocalAnimatedVisibilityScope
import io.github.realmazharhussain.smartnews.ui.common.navigation.SharedTransitionNavHost
import io.github.realmazharhussain.smartnews.ui.details.DetailsScreen
import io.github.realmazharhussain.smartnews.ui.home.HomeScreen
import kotlinx.serialization.Serializable

sealed interface Screen {
    @Serializable data object Home : Screen
    @Serializable data class Details(val articleId: Int, val articleUrl: String) : Screen
}

@Composable
fun SmartNewsApp() {
    val navController = rememberNavController()
    SharedTransitionNavHost(navController = navController, startDestination = Screen.Home) {
        composable<Screen.Home> {
            CompositionLocalProvider(LocalAnimatedVisibilityScope provides this) {
                HomeScreen(onArticleClicked = { navController.navigate(Screen.Details(it.id, it.url)) })
            }
        }

        composable<Screen.Details> {
            CompositionLocalProvider(LocalAnimatedVisibilityScope provides this) {
                DetailsScreen(route = it.toRoute())
            }
        }
    }
}
