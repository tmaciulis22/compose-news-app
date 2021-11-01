package com.example.test_app.api.network

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiResponseCall<T: Any>(
    private val delegate: Call<T>
): Call<ApiResponse<T>> {

    override fun enqueue(callback: Callback<ApiResponse<T>>) {
        return delegate.enqueue(object: Callback<T> {

            override fun onResponse(call: Call<T>, response: Response<T>) =
                callback.onResponse(this@ApiResponseCall, parse(response))

            override fun onFailure(call: Call<T>, t: Throwable) =
                callback.onResponse(
                    this@ApiResponseCall,
                    Response.success(ApiResponse.Error(body = t.message))
                )
        })
    }

    override fun clone(): Call<ApiResponse<T>> =
        ApiResponseCall(delegate.clone())

    override fun execute(): Response<ApiResponse<T>> = parse(delegate.execute())

    override fun isExecuted(): Boolean = delegate.isExecuted

    override fun cancel() = delegate.cancel()

    override fun isCanceled(): Boolean = delegate.isCanceled

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()

    private fun parse(response: Response<T>): Response<ApiResponse<T>> {
        val body = response.body()
        val code = response.code()

        val parsedResponse = if (response.isSuccessful) {
            ApiResponse.Success(body)
        } else {
            val errorBody = response.errorBody()?.string()

            if (!errorBody.isNullOrEmpty()) {
                ApiResponse.Error(code, errorBody)
            } else {
                val message = response.message()
                ApiResponse.Error(code, message)
            }
        }

        return Response.success(parsedResponse)
    }
}
