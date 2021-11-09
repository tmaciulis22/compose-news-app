package com.example.test_app.api.endpoint

import com.example.test_app.api.entity.Articles
import com.example.test_app.api.entity.SearchInAttribute
import com.example.test_app.api.network.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchEndpoint {

    @GET("search")
    suspend fun getArticles(
        @Query("q") queryText: String,
        @Query("from") fromDate: String? = null,
        @Query("to") toDate: String? = null,
        @Query("in") inAttribute: String =
            SearchInAttribute.getAttributesList(listOf(SearchInAttribute.Title, SearchInAttribute.Description))
    ): ApiResponse<Articles>
}
