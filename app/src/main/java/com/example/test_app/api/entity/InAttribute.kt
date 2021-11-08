package com.example.test_app.api.entity

enum class InAttribute(val value: String) {
    Title("title"),
    Description("description"),
    Content("content");

    companion object {
        fun getAttributesList(attributes: List<InAttribute>?) =
            attributes?.joinToString(",") ?: ""
    }
}
