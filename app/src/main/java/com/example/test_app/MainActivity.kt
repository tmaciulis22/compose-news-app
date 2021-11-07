package com.example.test_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Scaffold
import com.example.test_app.ui.theme.TestappTheme
import com.example.test_app.ui.view.TransparentStatusBar
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestappTheme {

                TransparentStatusBar()
                Scaffold(
                    bottomBar = {
                        // TODO
                    }
                ) {
                    AppNavHost()
                }
            }
        }
    }
}
