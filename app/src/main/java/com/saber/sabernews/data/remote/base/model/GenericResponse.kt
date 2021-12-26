package com.saber.sabernews.data.remote.base.model

typealias GenericResponse<S> = NetworkResponse<S, ServerError>

fun <T : Any> GenericResponse<T>.get(): T = when (this) {
    is NetworkResponse.Success -> body
    is NetworkResponse.ApiError -> throw NetworkException.build(this)
    is NetworkResponse.NetworkError -> throw error
    is NetworkResponse.UnknownError -> throw error
}