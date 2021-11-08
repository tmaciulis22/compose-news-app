package com.example.test_app.ui.navigation

import android.webkit.WebView
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.test_app.feature.UnimplementedScreen
import com.example.test_app.feature.news.NewsScreen
import com.example.test_app.feature.webView.WebViewScreen
import com.example.test_app.ui.navigation.Route
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@Composable
@ExperimentalAnimationApi
fun AppNavHost(navController: NavHostController) {
    AnimatedNavHost(navController = navController, startDestination = Route.News.name) {
        composable(
            Route.Home.name,
            enterTransition = { initial, _ ->
                when (initial.destination.route) {
                    Route.News.name,
                    Route.Search.name,
                    Route.Profile.name,
                    Route.More.name -> slideInHorizontally({ -it })
                    else -> slideInHorizontally({ it })
                }
            },
            exitTransition = { _, target ->
                when (target.destination.route) {
                    Route.News.name,
                    Route.Search.name,
                    Route.Profile.name,
                    Route.More.name -> slideOutHorizontally({ it })
                    else -> slideOutHorizontally({ -it })
                }
            },
            popEnterTransition = { initial, _ ->
                when (initial.destination.route) {
                    Route.News.name,
                    Route.Search.name,
                    Route.Profile.name,
                    Route.More.name -> slideInHorizontally({ -it })
                    else -> slideInHorizontally({ it })
                }
            },
            popExitTransition = { _, target ->
                when (target.destination.route) {
                    Route.News.name,
                    Route.Search.name,
                    Route.Profile.name,
                    Route.More.name -> slideOutHorizontally({ it })
                    else -> slideOutHorizontally({ -it })
                }
            }
        ) {
            UnimplementedScreen()
        }
        composable(
            Route.News.name,
            enterTransition = { initial, _ ->
                when (initial.destination.route) {
                    Route.Home.name -> slideInHorizontally({ it })
                    Route.Search.name,
                    Route.Profile.name,
                    Route.More.name -> slideInHorizontally({ -it })
                    else -> null
                }
            },
            exitTransition = { _, target ->
                when (target.destination.route) {
                    Route.Home.name -> slideOutHorizontally({ it })
                    Route.Search.name,
                    Route.Profile.name,
                    Route.More.name -> slideOutHorizontally({ -it })
                    else -> null
                }
            },
            popEnterTransition = { initial, _ ->
                when (initial.destination.route) {
                    Route.Home.name -> slideInHorizontally({ it })
                    Route.Search.name,
                    Route.Profile.name,
                    Route.More.name -> slideInHorizontally({ -it })
                    else -> null
                }
            },
            popExitTransition = { _, target ->
                when (target.destination.route) {
                    Route.Home.name -> slideOutHorizontally({ it })
                    Route.Search.name,
                    Route.Profile.name,
                    Route.More.name -> slideOutHorizontally({ -it })
                    else -> null
                }            }
        ) {
            NewsScreen(navController)
        }
        composable(
            Route.Search.name,
            enterTransition = { initial, _ ->
                when (initial.destination.route) {
                    Route.Profile.name,
                    Route.More.name -> slideInHorizontally({ -it })
                    else -> slideInHorizontally({ it })
                }
            },
            exitTransition = { _, target ->
                when (target.destination.route) {
                    Route.Profile.name,
                    Route.More.name  -> slideOutHorizontally({ -it })
                    else -> slideOutHorizontally({ it })
                }
            },
            popEnterTransition = { initial, _ ->
                when (initial.destination.route) {
                    Route.Profile.name,
                    Route.More.name  -> slideInHorizontally({ -it })
                    else -> slideInHorizontally({ it })
                }
            },
            popExitTransition = { _, target ->
                when (target.destination.route) {
                    Route.Profile.name,
                    Route.More.name  -> slideOutHorizontally({ -it })
                    else -> slideOutHorizontally({ it })
                }
            }
        ) {
            UnimplementedScreen()
        }
        composable(
            Route.Profile.name,
            enterTransition = { initial, _ ->
                when (initial.destination.route) {
                    Route.More.name -> slideInHorizontally({ -it })
                    else -> slideInHorizontally({ it })
                }
            },
            exitTransition = { _, target ->
                when (target.destination.route) {
                    Route.More.name  -> slideOutHorizontally({ -it })
                    else -> slideOutHorizontally({ it })
                }
            },
            popEnterTransition = { initial, _ ->
                when (initial.destination.route) {
                    Route.More.name  -> slideInHorizontally({ -it })
                    else -> slideInHorizontally({ it })
                }
            },
            popExitTransition = { _, target ->
                when (target.destination.route) {
                    Route.More.name  -> slideOutHorizontally({ -it })
                    else -> slideOutHorizontally({ it })
                }
            }
        ) {
            UnimplementedScreen()
        }
        composable(
            Route.More.name,
            enterTransition = { initial, _ ->
                when (initial.destination.route) {
                    Route.Home.name,
                    Route.News.name,
                    Route.Search.name,
                    Route.Profile.name -> slideInHorizontally({ it })
                    else -> slideInHorizontally({ -it })
                }
            },
            exitTransition = { _, target ->
                when (target.destination.route) {
                    Route.Home.name,
                    Route.News.name,
                    Route.Search.name,
                    Route.Profile.name -> slideOutHorizontally({ -it })
                    else -> slideOutHorizontally({ it })
                }
            },
            popEnterTransition = { initial, _ ->
                when (initial.destination.route) {
                    Route.Home.name,
                    Route.News.name,
                    Route.Search.name,
                    Route.Profile.name -> slideInHorizontally({ it })
                    else -> slideInHorizontally({ -it })
                }
            },
            popExitTransition = { _, target ->
                when (target.destination.route) {
                    Route.Home.name,
                    Route.News.name,
                    Route.Search.name,
                    Route.Profile.name -> slideOutHorizontally({ -it })
                    else -> slideOutHorizontally({ it })
                }
            }
        ) {
            UnimplementedScreen()
        }

        composable(
            Route.WebView.routeWithArgumentKey,
            enterTransition = { _, _ ->
                slideInHorizontally({ it })
            },
            exitTransition = { _, _ ->
                slideOutHorizontally({ it })
            },
            popEnterTransition = { _, _ ->
                slideInHorizontally({ it })
            },
            popExitTransition = { _, _ ->
                slideOutHorizontally({ it })
            }
        ) {
            WebViewScreen(
                navController,
                it.arguments?.getString(Route.WebView.argumentKey) ?: ""
            )
        }
    }
}
