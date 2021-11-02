package com.example.test_app.repository

import com.example.test_app.api.endpoint.HeadlinesEndpoint
import javax.inject.Inject


class HeadlinesRepository @Inject constructor(private val headlinesEndpoint: HeadlinesEndpoint) {

    suspend fun getTopHeadlines() = headlinesEndpoint.getTopHeadlines()
}
