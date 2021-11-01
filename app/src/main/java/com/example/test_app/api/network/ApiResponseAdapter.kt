package com.example.test_app.api.network

import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class ApiResponseAdapter<T: Any>(
    private val type: Type
) : CallAdapter<T, Call<ApiResponse<T>>> {

    override fun responseType(): Type = type

    override fun adapt(call: Call<T>): Call<ApiResponse<T>> = ApiResponseCall(call)
}
