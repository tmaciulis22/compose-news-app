package com.example.test_app

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.test_app.ui.navigation.AppNavHost
import com.example.test_app.ui.navigation.Route
import com.example.test_app.ui.theme.TestappTheme
import com.example.test_app.ui.view.BottomNavBar
import com.example.test_app.ui.view.TransparentStatusBar
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalAnimationApi
@ExperimentalMaterialNavigationApi
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestappTheme {
                val bottomSheetNavigator = rememberBottomSheetNavigator()
                val navController = rememberAnimatedNavController(bottomSheetNavigator)
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
                        ModalBottomSheetLayout(
                            bottomSheetNavigator,
                            sheetShape = RoundedCornerShape(
                                topStart = dimensionResource(R.dimen.grid_unit_10x),
                                topEnd = dimensionResource(R.dimen.grid_unit_10x)
                            ),
                            scrimColor = Color.Transparent
                        ) {
                            AppNavHost(navController)
                        }
                    }
                }
            }
        }
    }
}
