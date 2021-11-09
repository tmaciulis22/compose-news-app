package com.example.test_app.ui.navigation

import androidx.compose.animation.*
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.test_app.feature.UnimplementedScreen
import com.example.test_app.feature.filter.FilterScreen
import com.example.test_app.feature.filter.SearchInScreen
import com.example.test_app.feature.news.NewsScreen
import com.example.test_app.feature.search.SearchScreen
import com.example.test_app.feature.search.SearchViewModel
import com.example.test_app.feature.webView.WebViewScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation

@Composable
@ExperimentalAnimationApi
fun AppNavHost(navController: NavHostController) {
    AnimatedNavHost(navController = navController, startDestination = Route.SearchTab.name) {
        composable(
            Route.HomeTab.name,
            enterTransition = { initial, _ ->
                when (initial.destination.route) {
                    Route.NewsTab.name,
                    Route.Search.name,
                    Route.ProfileTab.name,
                    Route.MoreTab.name -> slideInHorizontally({ -it })
                    else -> slideInHorizontally({ it })
                }
            },
            exitTransition = { _, target ->
                when (target.destination.route) {
                    Route.NewsTab.name,
                    Route.Search.name,
                    Route.ProfileTab.name,
                    Route.MoreTab.name -> slideOutHorizontally({ it })
                    else -> slideOutHorizontally({ -it })
                }
            }
        ) {
            UnimplementedScreen()
        }
        composable(
            Route.NewsTab.name,
            enterTransition = { initial, _ ->
                when (initial.destination.route) {
                    Route.HomeTab.name -> slideInHorizontally({ it })
                    Route.Search.name,
                    Route.ProfileTab.name,
                    Route.MoreTab.name -> slideInHorizontally({ -it })
                    else -> null
                }
            },
            exitTransition = { _, target ->
                when (target.destination.route) {
                    Route.HomeTab.name -> slideOutHorizontally({ it })
                    Route.Search.name,
                    Route.ProfileTab.name,
                    Route.MoreTab.name -> slideOutHorizontally({ -it })
                    else -> null
                }
            }
        ) {
            NewsScreen(navController)
        }
        searchGraph(navController)
        composable(
            Route.ProfileTab.name,
            enterTransition = { initial, _ ->
                when (initial.destination.route) {
                    Route.MoreTab.name -> slideInHorizontally({ -it })
                    else -> slideInHorizontally({ it })
                }
            },
            exitTransition = { _, target ->
                when (target.destination.route) {
                    Route.MoreTab.name  -> slideOutHorizontally({ -it })
                    else -> slideOutHorizontally({ it })
                }
            }
        ) {
            UnimplementedScreen()
        }
        composable(
            Route.MoreTab.name,
            enterTransition = { initial, _ ->
                when (initial.destination.route) {
                    Route.HomeTab.name,
                    Route.NewsTab.name,
                    Route.Search.name,
                    Route.ProfileTab.name -> slideInHorizontally({ it })
                    else -> slideInHorizontally({ -it })
                }
            },
            exitTransition = { _, target ->
                when (target.destination.route) {
                    Route.HomeTab.name,
                    Route.NewsTab.name,
                    Route.Search.name,
                    Route.ProfileTab.name -> slideOutHorizontally({ -it })
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
        ) {
            WebViewScreen(
                navController,
                it.arguments?.getString(Route.WebView.argumentKey) ?: ""
            )
        }
    }
}

@ExperimentalAnimationApi
fun NavGraphBuilder.searchGraph(navController: NavHostController) {
    navigation(
        startDestination = Route.Search.name,
        route = Route.SearchTab.name,
        enterTransition = { initial, _ ->
            when (initial.destination.route) {
                Route.ProfileTab.name,
                Route.MoreTab.name -> slideInHorizontally({ -it })
                Route.Filter.name,
                Route.SearchIn.name,
                Route.WebView.name -> fadeIn()
                else -> slideInHorizontally({ it })
            }
        },
        exitTransition = { _, target ->
            when (target.destination.route) {
                Route.ProfileTab.name,
                Route.MoreTab.name  -> slideOutHorizontally({ -it })
                Route.Filter.name,
                Route.SearchIn.name,
                Route.WebView.name -> fadeOut()
                else -> slideOutHorizontally({ it })
            }
        },
    ) {
        composable(
            Route.Search.name
        ) {
            val parentEntry = remember {
                navController.getBackStackEntry(Route.SearchTab.name)
            }
            val searchViewModel = hiltViewModel<SearchViewModel>(
                parentEntry
            )
            SearchScreen(navController, searchViewModel)
        }
        composable(
            Route.Filter.name,
            enterTransition = { initial, _ ->
                when (initial.destination.route) {
                    Route.Search.name -> slideInHorizontally({ it })
                    else -> null
                }
            },
            exitTransition = { _, target ->
                when (target.destination.route) {
                    Route.Search.name -> slideOutHorizontally({ it })
                    else -> null
                }
            }
        ) {
            val parentEntry = remember {
                navController.getBackStackEntry(Route.SearchTab.name)
            }
            val searchViewModel = hiltViewModel<SearchViewModel>(
                parentEntry
            )
            FilterScreen(navController, searchViewModel)
        }
        composable(
            Route.SearchIn.name,
            enterTransition = { _, _ ->
                slideInHorizontally({ it })
            },
            exitTransition = { _, _ ->
                slideOutHorizontally({ it })
            }
        ) {
            val parentEntry = remember {
                navController.getBackStackEntry(Route.SearchTab.name)
            }
            val searchViewModel = hiltViewModel<SearchViewModel>(
                parentEntry
            )
            SearchInScreen(navController, searchViewModel)
        }
    }
}
