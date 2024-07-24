package io.github.realmazharhussain.smartnews.ui

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import androidx.paging.compose.collectAsLazyPagingItems
import io.github.realmazharhussain.smartnews.ui.common.LocalAnimatedContentScope
import io.github.realmazharhussain.smartnews.ui.common.LocalSharedTransitionScope
import io.github.realmazharhussain.smartnews.ui.details.DetailsScreen
import io.github.realmazharhussain.smartnews.ui.details.DetailsViewModel
import io.github.realmazharhussain.smartnews.ui.home.HomeScreen
import io.github.realmazharhussain.smartnews.ui.home.NewsViewModel
import kotlinx.serialization.Serializable

sealed interface Screen {
    @Serializable data object Home : Screen
    @Serializable data class Details(val articleId: Int, val articleUrl: String) : Screen
}

@Composable
fun SmartNewsApp(modifier: Modifier = Modifier) {
    @OptIn(ExperimentalSharedTransitionApi::class)
    SharedTransitionLayout {
        val navController = rememberNavController()
        CompositionLocalProvider(LocalSharedTransitionScope provides this) {
            NavHost(
                navController = navController,
                startDestination = Screen.Home,
                modifier = modifier,
            ) {
                composable<Screen.Home> {
                    val viewModel: NewsViewModel = hiltViewModel()
                    val items = viewModel.everything.collectAsLazyPagingItems()
                    CompositionLocalProvider(LocalAnimatedContentScope provides this) {
                        HomeScreen(items, onArticleClicked = { navController.navigate(Screen.Details(it.id, it.url)) })
                    }
                }

                composable<Screen.Details> {
                    val viewModel: DetailsViewModel = hiltViewModel()
                    val details by remember { viewModel.getDetails(it.toRoute<Screen.Details>().articleId) }.collectAsState()
                    val summary by remember { viewModel.getSummary(it.toRoute<Screen.Details>().articleUrl) }.collectAsState()
                    CompositionLocalProvider(LocalAnimatedContentScope provides this) {
                        DetailsScreen(details, summary)
                    }
                }
            }
        }
    }
}
