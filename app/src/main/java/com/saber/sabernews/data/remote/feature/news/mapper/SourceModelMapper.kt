package com.saber.sabernews.data.remote.feature.news.mapper

import com.saber.sabernews.data.data.feature.news.model.SourceEntity
import com.saber.sabernews.data.remote.base.mapper.ModelMapper
import com.saber.sabernews.data.remote.feature.news.model.SourceModel
import javax.inject.Inject

open class SourceModelMapper @Inject constructor() :
    ModelMapper<SourceModel, SourceEntity> {

    override fun mapFromModel(model: SourceModel): SourceEntity {
        return SourceEntity(
            id = model.id,
            name = model.name,
        )
    }
}