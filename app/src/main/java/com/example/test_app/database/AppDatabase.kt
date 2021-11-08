package com.example.test_app.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.test_app.database.dao.SearchHistoryDao
import com.example.test_app.database.entity.SearchQuery

@Database(entities = [SearchQuery::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun searchHistoryDao(): SearchHistoryDao
}
