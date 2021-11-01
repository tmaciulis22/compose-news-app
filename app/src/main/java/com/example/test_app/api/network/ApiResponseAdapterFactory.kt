package com.example.test_app.api.network

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ApiResponseAdapterFactory : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != Call::class.java) return null

        check(returnType is ParameterizedType) {
            "Return type must be parameterized as Call<ApiResponse<<Foo>> or Call<ApiResponse<out Foo>>"
        }

        val responseType = getParameterUpperBound(0, returnType)
        if (getRawType(responseType) != ApiResponse::class.java) return null

        check(responseType is ParameterizedType) {
            "Response must be parameterized as ApiResponse<Foo> or ApiResponse<out Foo>"
        }

        return ApiResponseAdapter<Any>(getParameterUpperBound(0, responseType))
    }
}
