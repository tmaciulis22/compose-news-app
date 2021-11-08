package com.example.test_app.ui.view

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.test_app.ui.navigation.Route
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@Composable
fun BottomNavBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation {
        Row(modifier = Modifier.background(MaterialTheme.colors.surface)) {
            Route.bottomNavItems.forEach { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            painter = painterResource(item.iconResId ?: -1),
                            contentDescription = stringResource(item.labelResId ?: -1)
                        )
                    },
                    label = { Text(stringResource(item.labelResId ?: -1)) },
                    selected = currentDestination?.hierarchy?.any { it.route == item.name } == true,
                    onClick = {
                        navController.navigate(item.name) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    selectedContentColor = MaterialTheme.colors.primary,
                    unselectedContentColor = MaterialTheme.colors.secondary
                )
            }
        }
    }
}

@Preview
@Composable
@ExperimentalAnimationApi
fun BottomNavBarPreview() {
    val navController = rememberAnimatedNavController()

    BottomNavBar(navController = navController)
}
