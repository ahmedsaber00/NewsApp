package com.saber.sabernews.data.data.feature.news

import com.saber.sabernews.data.data.feature.news.mapper.NewsResponseMapper
import com.saber.sabernews.data.data.feature.news.store.NewsDataStoreFactory
import com.saber.sabernews.domain.feature.news.NewsRepository
import com.saber.sabernews.domain.feature.news.model.NewsResponse
import javax.inject.Inject

class NewsDataRepository @Inject constructor(
    private val mapperNews: NewsResponseMapper,
    private val factory: NewsDataStoreFactory
) : NewsRepository {

    override suspend fun getAllNews(
        pageNumber: Int,
        pageSize: Int,
        searchQuery: String
    ): NewsResponse =
        mapperNews.mapFromEntity(
            factory.getRemoteDataStore()
                .getAllNews(pageNumber, pageSize, searchQuery)
        )
}