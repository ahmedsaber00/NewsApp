package com.saber.sabernews.data.data.feature.news.repository

import com.saber.sabernews.data.data.feature.news.model.NewsResponseEntity

interface NewsRemote : NewsDataStore {

    suspend fun getAllNews(
        pageNumber: Int,
        pageSize: Int,
        searchQuery: String
    ): NewsResponseEntity

}