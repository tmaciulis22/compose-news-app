package com.example.test_app.feature.webView

import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.test_app.ui.view.AppBar
import com.example.test_app.ui.view.ArrowBackButton

@Composable
fun WebViewScreen(
    navController: NavController,
    url: String
) {
    Column {
        AppBar(
            isRounded = false,
            title = {
                Text(
                    text = url,
                    style = MaterialTheme.typography.h1,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            navigationButton = {
                ArrowBackButton {
                    navController.popBackStack()
                }
            }
        )
        AndroidView(
            factory = {
                WebView(it).apply {
                    webViewClient = object : WebViewClient() {
                        override fun shouldOverrideUrlLoading(
                            view: WebView?,
                            request: WebResourceRequest?
                        ): Boolean {
                            return false
                        }
                    }
                }
            }, update = {
                it.loadUrl(url)
            }
        )
    }
}
