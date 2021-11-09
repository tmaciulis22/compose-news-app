package com.example.test_app.api.entity

import androidx.annotation.StringRes
import com.example.test_app.R

enum class SortBy(val value: String) {
    PublishedAt("publishedAt"),
    Relevance("relevance");
}
