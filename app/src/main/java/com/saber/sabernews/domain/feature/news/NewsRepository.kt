package com.saber.sabernews.domain.feature.news

import com.saber.sabernews.domain.feature.news.model.NewsResponse

interface NewsRepository {

    suspend fun getAllNews(
        pageNumber: Int,
        pageSize: Int,
        searchQuery: String
    ): NewsResponse

}