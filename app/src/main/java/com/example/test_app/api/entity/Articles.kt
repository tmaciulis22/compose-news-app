package com.example.test_app.api.entity

data class Articles(
    val totalArticles: Int,
    val articles: List<Article>
) {
    companion object {
        // Due to GNews api limitation only 10 articles can be retrieved per request
        const val MAX_ARTICLES = 10
    }
}
