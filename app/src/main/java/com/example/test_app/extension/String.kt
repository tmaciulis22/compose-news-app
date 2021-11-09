package com.example.test_app.extension

fun String?.formatDateToISO(): String? {
    if (this == null) return null

    return this.replace("/", "-") + "T00:00:00Z"
}
