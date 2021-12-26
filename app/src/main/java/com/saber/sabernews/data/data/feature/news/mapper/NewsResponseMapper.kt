package com.saber.sabernews.data.data.feature.news.mapper

import com.saber.sabernews.data.data.feature.news.model.NewsResponseEntity
import com.saber.sabernews.data.data.mapper.EntityMapper
import com.saber.sabernews.domain.feature.news.model.NewsResponse
import javax.inject.Inject

class NewsResponseMapper @Inject constructor(
    private val mapperNews: NewsMapper
) : EntityMapper<NewsResponseEntity, NewsResponse> {

    override fun mapFromEntity(entity: NewsResponseEntity) =
        NewsResponse(
            state = entity.state,
            totalResults = entity.totalResults,
            articles = entity.articles?.let { list ->
                list.map {
                    mapperNews.mapFromEntity(
                        it
                    )
                }
            },
        )

    override fun mapToEntity(domain: NewsResponse) =
        NewsResponseEntity(
            state = domain.state,
            totalResults = domain.totalResults,
            articles = domain.articles?.let { list ->
                list.map {
                    mapperNews.mapToEntity(
                        it
                    )
                }
            },
        )
}