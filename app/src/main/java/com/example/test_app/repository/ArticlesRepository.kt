package com.example.test_app.repository

import com.example.test_app.api.endpoint.HeadlinesEndpoint


class ArticlesRepository(private val headlinesEndpoint: HeadlinesEndpoint) {

    suspend fun getTopHeadlines() = headlinesEndpoint.getTopHeadlines()
}
