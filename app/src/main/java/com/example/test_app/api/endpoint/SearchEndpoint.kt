package com.example.test_app.api.endpoint

import com.example.test_app.api.entity.Articles
import com.example.test_app.api.entity.InAttribute
import com.example.test_app.api.network.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchEndpoint {

    @GET("search")
    suspend fun getArticles(
        @Query("from") fromDate: String? = null,
        @Query("to") toDate: String? = null,
        @Query("in") inAttribute: String =
            InAttribute.getAttributesList(listOf(InAttribute.Title, InAttribute.Description))
    ): ApiResponse<Articles>
}
