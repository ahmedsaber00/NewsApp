package com.saber.sabernews.domain.base.result

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class AResult<out R> {
    data class Success<out T>(val data: T) : AResult<T>()
    data class Error(val exception: Exception) : AResult<Nothing>()
    object Loading : AResult<Nothing>()
}

val <T> AResult<T>.data: T?
    get() = (this as? AResult.Success)?.data