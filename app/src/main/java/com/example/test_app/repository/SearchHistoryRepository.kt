package com.example.test_app.repository

import com.example.test_app.database.dao.SearchHistoryDao
import com.example.test_app.database.entity.SearchQuery
import javax.inject.Inject

class SearchHistoryRepository @Inject constructor(
    private val searchHistoryDao: SearchHistoryDao
) {

    suspend fun getAllQueries() = searchHistoryDao.getAll()

    suspend fun insertNewQuery(queryText: String) = searchHistoryDao.insert(SearchQuery(queryText = queryText))
}
