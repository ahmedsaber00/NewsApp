package com.saber.sabernews.data.remote.feature.news.mapper

import com.saber.sabernews.data.data.feature.news.model.NewsResponseEntity
import com.saber.sabernews.data.remote.base.mapper.ModelMapper
import com.saber.sabernews.data.remote.feature.news.model.NewsResponseModel
import javax.inject.Inject

open class NewsResponseModelMapper @Inject constructor(
    private val mapperNewsModel: NewsModelMapper,
) : ModelMapper<NewsResponseModel, NewsResponseEntity> {

    override fun mapFromModel(model: NewsResponseModel): NewsResponseEntity {
        return NewsResponseEntity(
            state = model.state,
            totalResults = model.totalResults,
            articles = model.articles?.let { list ->
                list.map {
                    mapperNewsModel.mapFromModel(
                        it
                    )
                }
            },
        )
    }
}