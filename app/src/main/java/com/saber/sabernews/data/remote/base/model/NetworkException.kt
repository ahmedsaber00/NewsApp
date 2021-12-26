package com.saber.sabernews.data.remote.base.model

import retrofit2.Response

/**
 *
 * @property url The request URL which produced the error.
 * @property response Response object containing status code, headers, body, etc.
 */
class NetworkException(
    val serverError: ServerError,
    val url: String?,
    val response: Response<*>?,
    val code: Int?
) : RuntimeException(serverError.message) {

    companion object {
        fun build(error: NetworkResponse.ApiError<ServerError>): NetworkException {
            return NetworkException(error.body, error.url, error.response, error.code)
        }
    }

}