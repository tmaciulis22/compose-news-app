package com.example.test_app

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.test_app.ui.navigation.AppNavHost
import com.example.test_app.ui.navigation.Route
import com.example.test_app.ui.theme.TestappTheme
import com.example.test_app.ui.view.BottomNavBar
import com.example.test_app.ui.view.TransparentStatusBar
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalAnimationApi
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestappTheme {
                val navController = rememberAnimatedNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                TransparentStatusBar()
                Scaffold(
                    bottomBar = {
                        if (Route.bottomNavItemsRoutes.any { it == currentRoute })
                            BottomNavBar(navController)
                    }
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        AppNavHost(navController)
                    }
                }
            }
        }
    }
}
