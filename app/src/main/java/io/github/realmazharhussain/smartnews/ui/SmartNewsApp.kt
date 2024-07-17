package io.github.realmazharhussain.smartnews.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
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
    NavHost(navController = navController, startDestination = Screen.Home) {
        composable<Screen.Home> { HomeScreen(onArticleClicked = { navController.navigate(Screen.Details(it.id, it.url)) }) }
        composable<Screen.Details> { DetailsScreen(it.toRoute()) }
    }
}
