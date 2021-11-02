package com.example.test_app.ui

// State interface for screens, which fetch data from repository
interface DataScreenState<T> {
    val isLoading: Boolean
    val isError: Boolean
    val data: T?
}
