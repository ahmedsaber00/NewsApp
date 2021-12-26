package com.saber.sabernews.data.remote.feature.news.mapper

import com.saber.sabernews.data.data.feature.news.model.NewsEntity
import com.saber.sabernews.data.remote.base.mapper.ModelMapper
import com.saber.sabernews.data.remote.feature.news.model.NewsModel
import javax.inject.Inject

open class NewsModelMapper @Inject constructor(
    private val mapperSourceModel: SourceModelMapper,
) : ModelMapper<NewsModel, NewsEntity> {

    override fun mapFromModel(model: NewsModel): NewsEntity {
        return NewsEntity(
            source = model.source?.let { mapperSourceModel.mapFromModel(it) },
            author = model.author,
            title = model.title,
            description = model.description,
            url = model.url,
            urlToImage = model.urlToImage,
            publishedAt = model.publishedAt,
            content = model.content,
        )
    }

}