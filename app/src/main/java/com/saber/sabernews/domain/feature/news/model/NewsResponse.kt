package com.saber.sabernews.domain.feature.news.model

data class NewsResponse(
    val state: String? = null,
    val totalResults: Int? = null,
    val articles: List<News>? = null
)