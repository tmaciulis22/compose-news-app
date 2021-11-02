package com.example.test_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.test_app.feature.news.NewsScreen
import com.example.test_app.feature.webView.WebViewScreen
import com.example.test_app.ui.theme.TestappTheme
import com.example.test_app.ui.view.AppBar
import com.example.test_app.ui.view.TransparentStatusBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestappTheme {
                val navHostController = rememberNavController()

                TransparentStatusBar()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        AppBar()
                    },
                    bottomBar = {
                        // TODO BottomBar
                    }
                ) {
                    // TODO create screen routes enum
                    NavHost(navHostController, "news") {
                        composable("news") { NewsScreen(navHostController) }
                        composable("webView/{url}") {
                            WebViewScreen(
                                navHostController,
                                it.arguments?.getString("url") ?: ""
                            )
                        }
                    }
                }
            }
        }
    }
}
