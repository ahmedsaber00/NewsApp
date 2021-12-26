package com.saber.sabernews.data.data.feature.news.mapper

import com.saber.sabernews.data.data.feature.news.model.SourceEntity
import com.saber.sabernews.data.data.mapper.EntityMapper
import com.saber.sabernews.domain.feature.news.model.Source
import javax.inject.Inject

class SourceMapper @Inject constructor() : EntityMapper<SourceEntity, Source> {

    override fun mapFromEntity(entity: SourceEntity) =
        Source(
            id = entity.id,
            name = entity.name,
        )

    override fun mapToEntity(domain: Source) =
        SourceEntity(
            id = domain.id,
            name = domain.name,
        )
}