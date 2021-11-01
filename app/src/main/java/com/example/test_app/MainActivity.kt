package com.example.test_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.example.test_app.feature.news.NewsViewModel
import com.example.test_app.ui.articles.ArticlesList
import com.example.test_app.ui.theme.TestappTheme
import com.example.test_app.ui.view.AppBar
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestappTheme {
                SetStatusBarColorTransparent()
                val viewModel: NewsViewModel by viewModels()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        AppBar()
                    },
                    bottomBar = { Text(text = "hey")}
                ) {
                    ArticlesList(title = "News", isLoading = true)
                }
            }
        }
    }
}

@Composable
fun SetStatusBarColorTransparent() {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = MaterialTheme.colors.isLight

    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = useDarkIcons
        )
    }
}
