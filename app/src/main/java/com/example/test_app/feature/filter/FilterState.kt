package com.example.test_app.feature.filter

import com.example.test_app.api.entity.SearchInAttribute
import com.example.test_app.api.entity.SortBy

data class FilterState(
    val queryText: String? = null,
    val from: String? = null,
    val to: String? = null,
    val searchIn: List<SearchInAttribute> = listOf(),
    val sortBy: SortBy? = null
) {
    val count: Int
        get() {
            var totalCount = 0

            from?.let { totalCount++ }
            to?.let { totalCount++ }
            if (searchIn.count() > 0)
                totalCount++

            return totalCount
        }
}
