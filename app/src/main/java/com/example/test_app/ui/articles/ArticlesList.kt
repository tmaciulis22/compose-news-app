package com.example.test_app.ui.articles

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import com.example.test_app.R
import com.example.test_app.api.entity.Articles
import com.example.test_app.api.entity.Articles.Companion.MAX_ARTICLES

@Composable
fun ArticlesList(
    title: String,
    isLoading: Boolean = false,
    articles: Articles? = null,
    onArticleClick: (String) -> Unit = {}
) {
    LazyColumn(
        contentPadding = PaddingValues(
            horizontal = dimensionResource(id = R.dimen.grid_unit_9x),
            vertical = dimensionResource(id = R.dimen.grid_unit_15x)
        ),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.grid_unit_5x))
    ) {
        item {
            Text(
                text = title,
                style = MaterialTheme.typography.h1
            )
        }
        when {
            articles != null -> items(articles.articles) { article ->
                article.apply {
                    ArticleCard(
                        imageUrl = image,
                        title = this.title,
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
