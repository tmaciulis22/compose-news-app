package com.example.test_app

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import com.example.test_app.feature.news.NewsScreen
import com.example.test_app.feature.webView.WebViewScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@ExperimentalAnimationApi
@Composable
fun AppNavHost() {
    val navController = rememberAnimatedNavController()

    AnimatedNavHost(navController = navController, startDestination = Routes.News.name) {
        composable(
            Routes.News.name,
            enterTransition = { _, _ ->
                slideInHorizontally({ -it / 2 })
            },
            exitTransition = { _, _ ->
                slideOutHorizontally({ it / 2 })
            },
            popEnterTransition = { _, _ ->
                slideInHorizontally({ -it / 2 })
            },
            popExitTransition = { _, _ ->
                slideOutHorizontally({ it / 2 })
            }
        ) {
            NewsScreen(navController)
        }
        composable(
            Routes.WebView.routeWithArgumentKey,
            enterTransition = { _, _ ->
                slideInHorizontally({ it / 2 })
            },
            exitTransition = { _, _ ->
                slideOutHorizontally({ -it / 2 })
            },
            popEnterTransition = { _, _ ->
                slideInHorizontally({ it / 2 })
            },
            popExitTransition = { _, _ ->
                slideOutHorizontally({ -it / 2 })
            }
        ) {
            WebViewScreen(
                navController,
                it.arguments?.getString(Routes.WebView.argumentKey) ?: ""
            )
        }
    }
}
