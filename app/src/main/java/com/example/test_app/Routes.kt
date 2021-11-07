package com.example.test_app

enum class Routes(
    val argumentKey: String? = null
) {
    News,
    WebView("url");

    val routeWithArgumentKey: String = "$name/{$argumentKey}"

    fun generateFullRoute(argument: String) = "$name/$argument"
}
