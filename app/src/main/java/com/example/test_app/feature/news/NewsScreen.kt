package com.example.test_app.feature.news

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.test_app.R
import com.example.test_app.ui.articles.ArticlesList
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun NewsScreen(
    navController: NavController,
    viewModel: NewsViewModel = hiltViewModel()
) {
    val state = viewModel.newsScreenState

    ArticlesList(
        title = stringResource(R.string.articles_list_title),
        isLoading = state.isLoading,
        articles = state.data,
        onArticleClick = { url ->
            val encodedUrl = URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
            navController.navigate(route = "webView/$encodedUrl")
        }
    )
}
