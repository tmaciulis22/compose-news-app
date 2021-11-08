package com.example.test_app.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.test_app.database.entity.SearchQuery

@Dao
interface SearchHistoryDao {

    @Query("SELECT * FROM searchquery")
    suspend fun getAll(): List<SearchQuery>

    @Insert
    suspend fun insert(searchQuery: SearchQuery)
}
