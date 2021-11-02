package com.example.test_app.api.endpoint

import com.example.test_app.api.entity.Articles
import com.example.test_app.api.network.ApiResponse
import retrofit2.http.GET

interface HeadlinesEndpoint {

    @GET("top-headlines")
    suspend fun getTopHeadlines(): ApiResponse<Articles>
}
