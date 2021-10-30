package com.example.test_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.example.test_app.ui.theme.TestappTheme
import com.example.test_app.ui.view.AppBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestappTheme {
                Surface(color = MaterialTheme.colors.background) {
                    AppBar()
                }
            }
        }
    }
}
