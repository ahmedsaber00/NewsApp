package com.saber.sabernews.data.data.feature.news.mapper

import com.saber.sabernews.data.data.feature.news.model.NewsEntity
import com.saber.sabernews.data.data.mapper.EntityMapper
import com.saber.sabernews.domain.feature.news.model.News
import javax.inject.Inject

class NewsMapper @Inject constructor(
    private val mapperSource: SourceMapper,
) : EntityMapper<NewsEntity, News> {

    override fun mapFromEntity(entity: NewsEntity) =
        News(
            source = entity.source?.let { mapperSource.mapFromEntity(it) },
            author = entity.author,
            title = entity.title,
            description = entity.description,
            url = entity.url,
            urlToImage = entity.urlToImage,
            publishedAt = entity.publishedAt,
            content = entity.content,
        )

    override fun mapToEntity(domain: News) =
        NewsEntity(
            source = domain.source?.let { mapperSource.mapToEntity(it) },
            author = domain.author,
            title = domain.title,
            description = domain.description,
            url = domain.url,
            urlToImage = domain.urlToImage,
            publishedAt = domain.publishedAt,
            content = domain.content,
        )
}