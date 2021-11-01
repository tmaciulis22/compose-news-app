package com.example.test_app.ui.articles

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.dimensionResource
import com.example.test_app.R
import com.example.test_app.api.entity.Articles
import com.example.test_app.di.ApiModule.MAX_ARTICLES

@Composable
fun ArticlesList(
    title: String,
    isLoading: Boolean = false,
    articles: Articles? = null,
    onArticleClick: (String) -> Unit = {}
) {
    Column(horizontalAlignment = Alignment.Start) {
        Text(text = title)
        LazyColumn(
            contentPadding = PaddingValues(dimensionResource(id = R.dimen.grid_unit_9x)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.grid_unit_5x))
        ) {
            when {
                articles != null -> items(articles.articles) { article ->
                    article.apply {
                        ArticleCard(
                            imageUrl = image,
                            title = title,
                            description = description,
                            articleUrl = url,
                            onClick = onArticleClick
                        )
                    }
                }
                isLoading -> items(MAX_ARTICLES) {
                    ArticleCard(isLoading = true)
                }
            }
        }
    }
}
