package com.example.test_app.repository

import com.example.test_app.api.endpoint.SearchEndpoint
import com.example.test_app.api.entity.InAttribute
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val searchEndpoint: SearchEndpoint
) {

    suspend fun getArticles(
        from: String? = null,
        to: String? = null,
        inAttributes: List<InAttribute>? = null
    ) = searchEndpoint.getArticles(from, to, InAttribute.getAttributesList(inAttributes))
}
