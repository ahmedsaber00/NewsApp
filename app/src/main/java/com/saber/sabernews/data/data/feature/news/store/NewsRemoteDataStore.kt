package com.saber.sabernews.data.data.feature.news.store

import com.saber.sabernews.BuildConfig
import com.saber.sabernews.data.data.feature.news.model.NewsResponseEntity
import com.saber.sabernews.data.data.feature.news.repository.NewsRemote
import com.saber.sabernews.data.remote.base.model.get
import com.saber.sabernews.data.remote.feature.news.mapper.NewsResponseModelMapper
import com.saber.sabernews.data.remote.feature.news.service.NewsService
import com.saber.sabernews.domain.base.executor.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

open class NewsRemoteDataStore @Inject constructor(
    private val service: NewsService,
    private val mapperNews: NewsResponseModelMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : NewsRemote {

    override suspend fun getAllNews(
        pageNumber: Int,
        pageSize: Int,
        searchQuery: String
    ): NewsResponseEntity = withContext(ioDispatcher) {
        val params = HashMap<String, String>()
        params["page"] = pageNumber.toString()
        params["pageSize"] = pageSize.toString()
        params["q"] = searchQuery.toString()
        params["apiKey"] = BuildConfig.API_KEY
        mapperNews.mapFromModel(
            service.getAllNews(params).get()
        )
    }
}