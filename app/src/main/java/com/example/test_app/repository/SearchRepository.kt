package com.example.test_app.repository

import com.example.test_app.api.endpoint.SearchEndpoint
import com.example.test_app.api.entity.SearchInAttribute
import com.example.test_app.api.entity.SortBy
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val searchEndpoint: SearchEndpoint
) {

    suspend fun getArticles(
        queryText: String,
        from: String? = null,
        to: String? = null,
        searchInAttributes: List<SearchInAttribute>? = null,
        sortBy: SortBy? = null
    ) = searchEndpoint.getArticles(
        queryText,
        from,
        to,
        SearchInAttribute.getAttributesList(searchInAttributes),
        sortBy ?: SortBy.PublishedAt
    )
}
