package com.saber.sabernews.data.data.feature.news.model

data class NewsResponseEntity(
    val state: String? = null,
    val totalResults: Int? = null,
    val articles: List<NewsEntity>? = null
)
