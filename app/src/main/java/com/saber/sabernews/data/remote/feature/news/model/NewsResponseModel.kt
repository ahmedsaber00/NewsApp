package com.saber.sabernews.data.remote.feature.news.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsResponseModel(
    @Json(name = "state")
    val state: String? = null,
    @Json(name = "totalResults")
    val totalResults: Int? = null,
    @Json(name = "articles")
    val articles: List<NewsModel>? = null
)
