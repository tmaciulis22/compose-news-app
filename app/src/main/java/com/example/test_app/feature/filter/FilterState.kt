package com.example.test_app.feature.filter

data class FilterState(
    val from: String? = null,
    val to: String? = null,
    val searchIn: String? = null
) {
    val count: Int
        get() {
            var totalCount = 0

            from?.let { totalCount++ }
            to?.let { totalCount++ }
            searchIn?.let { totalCount++ }

            return totalCount
        }
}
