package com.saber.sabernews.data.data.feature.news.model

data class NewsEntity(
    val source: SourceEntity? = null,
    val author: String? = null,
    val title: String? = null,
    val description: String? = null,
    val url: String? = null,
    val urlToImage: String? = null,
    val publishedAt: String? = null,
    val content: String? = null,
)
