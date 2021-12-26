package com.saber.sabernews.data.data.feature.news.store

import com.saber.sabernews.data.data.feature.news.repository.NewsRemote
import javax.inject.Inject

open class NewsDataStoreFactory @Inject constructor(
    private val remote: NewsRemote
) {
    open fun getRemoteDataStore(): NewsRemote = remote
}