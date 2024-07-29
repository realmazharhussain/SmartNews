package io.github.realmazharhussain.smartnews.ui

import androidx.compose.runtime.Composable
import androidx.navigation.toRoute
import io.github.realmazharhussain.smartnews.ui.common.navigation.SharedTransitionNavHost
import io.github.realmazharhussain.smartnews.ui.common.navigation.composable
import io.github.realmazharhussain.smartnews.ui.details.DetailsScreen
import io.github.realmazharhussain.smartnews.ui.home.HomeScreen
import kotlinx.serialization.Serializable

sealed interface Screen {
    @Serializable data object Home : Screen
    @Serializable data class Details(val articleId: Int, val articleUrl: String) : Screen
}

@Composable
fun SmartNewsApp() {
    SharedTransitionNavHost(startDestination = Screen.Home) {
        composable<Screen.Home> { HomeScreen(onArticleClicked = { navController.navigate(Screen.Details(it.id, it.url)) }) }
        composable<Screen.Details> { DetailsScreen(route = it.toRoute()) }
    }
}
