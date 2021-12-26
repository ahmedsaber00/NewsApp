package com.saber.sabernews.presentation.features.news.mapper

import com.saber.sabernews.domain.feature.news.model.NewsResponse
import com.saber.sabernews.presentation.base.mapper.Mapper
import com.saber.sabernews.presentation.features.news.model.NewsResponseView
import javax.inject.Inject

class NewsResponseViewMapper @Inject constructor(
    private val mapperNews: NewsViewMapper,
) : Mapper<NewsResponseView, NewsResponse> {

    override fun mapToView(type: NewsResponse): NewsResponseView {
        return NewsResponseView(
            state = type.state,
            totalResults = type.totalResults,
            articles = type.articles?.let { list ->
                list.map {
                    mapperNews.mapToView(
                        it
                    )
                }
            },
        )
    }

}