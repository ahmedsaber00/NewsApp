package com.saber.sabernews.presentation.features.news.mapper

import com.saber.sabernews.domain.feature.news.model.News
import com.saber.sabernews.presentation.base.mapper.Mapper
import com.saber.sabernews.presentation.features.news.model.NewsView
import javax.inject.Inject

class NewsViewMapper @Inject constructor(
    private val mapperSource: SourceViewMapper,
) : Mapper<NewsView, News> {

    override fun mapToView(type: News): NewsView {
        return NewsView(
            source = type.source?.let { mapperSource.mapToView(it) },
            author = type.author,
            title = type.title,
            description = type.description,
            url = type.url,
            urlToImage = type.urlToImage,
            publishedAt = type.publishedAt,
            content = type.content,
        )
    }

}