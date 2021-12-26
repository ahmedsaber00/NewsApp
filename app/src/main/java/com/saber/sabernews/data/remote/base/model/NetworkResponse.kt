package com.saber.sabernews.data.remote.base.model

import retrofit2.Response
import java.io.IOException

sealed class NetworkResponse<out T : Any, out U : Any> {
    /**
     * Success response with body
     */
    data class Success<T : Any>(val body: T) : NetworkResponse<T, Nothing>()

    /**
     * Failure response with body
     *
     * @property url The request URL which produced the error.
     * @property response Response object containing status code, headers, body, etc.
     */
    data class ApiError<U : Any>(
        val body: U,
        val code: Int,
        val url: String,
        val response: Response<*>?
    ) : NetworkResponse<Nothing, U>()

    /**
     * Network error
     */
    data class NetworkError(val error: IOException) : NetworkResponse<Nothing, Nothing>()

    /**
     * For example, json parsing error
     */
    data class UnknownError(val error: Throwable) : NetworkResponse<Nothing, Nothing>()
}