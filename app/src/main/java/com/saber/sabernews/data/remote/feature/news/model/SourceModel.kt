package com.saber.sabernews.data.remote.feature.news.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SourceModel(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "name")
    val name: String? = null,
)