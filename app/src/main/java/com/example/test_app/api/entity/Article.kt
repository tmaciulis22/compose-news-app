package com.example.test_app.api.entity

import java.util.*

data class Article(
    val title: String,
    val description: String,
    val content: String,
    val url: String,
    val image: String,
    val publishedAt: Date,
    val source: Source
)

data class Source(
    val name: String,
    val url: String
)
