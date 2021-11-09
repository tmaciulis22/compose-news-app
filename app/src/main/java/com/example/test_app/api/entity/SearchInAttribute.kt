package com.example.test_app.api.entity

import androidx.annotation.StringRes
import com.example.test_app.R

enum class SearchInAttribute(val value: String, @StringRes val textRes: Int) {
    Title("title", R.string.filters_search_in_title),
    Description("description", R.string.filters_search_in_description),
    Content("content", R.string.filters_search_in_content);

    companion object {
        val allAttributes: List<SearchInAttribute>
            get() = listOf(Title, Description, Content)

        fun getAttributeByName(name: String): SearchInAttribute =
            allAttributes.first { it.name == name }

        fun getAttributesList(attributes: List<SearchInAttribute>?) =
            attributes?.joinToString(",") { it.value } ?: ""
    }
}
