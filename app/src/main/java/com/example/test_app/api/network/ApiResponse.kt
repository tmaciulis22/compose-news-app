package com.example.test_app.api.network

sealed class ApiResponse<out T : Any> {

    data class Success<T: Any>(val payload: T?) : ApiResponse<T>() {
        val nonNullPayload: T
            get() = payload
                ?: throw NullPointerException("Non null response payload requested, but null found")
    }

    data class Error(
        val code: Int = UNKNOWN,
        val body: String? = null
    ) : ApiResponse<Nothing>()

    suspend fun get(
        onSuccess: suspend (payload: T?) -> Unit = {},
        onFailure: suspend (code: Int, body: String?) -> Unit = { _, _ -> },
        finally: suspend () -> Unit = {}
    ) {
        when (this) {
            is Success -> onSuccess.invoke(payload)
            is Error -> onFailure.invoke(code, body)
        }
        finally()
    }

    suspend fun getNonNull(
        onSuccess: suspend (payload: T) -> Unit = {},
        onFailure: suspend (code: Int, body: String?) -> Unit = {_, _ -> },
        finally: suspend () -> Unit = {}
    ) {
        when (this) {
            is Success -> onSuccess.invoke(nonNullPayload)
            is Error -> onFailure.invoke(code, body)
        }
        finally()
    }

    companion object {
        const val UNKNOWN = -1
    }
}
