package com.saber.sabernews.data.remote.base.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ServerError(
    @Json(name = "code") val code: Int,
    @Json(name = "message") val message: String
)