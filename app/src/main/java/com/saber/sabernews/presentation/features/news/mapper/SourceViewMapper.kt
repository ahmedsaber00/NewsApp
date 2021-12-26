package com.saber.sabernews.presentation.features.news.mapper

import com.saber.sabernews.domain.feature.news.model.Source
import com.saber.sabernews.presentation.base.mapper.Mapper
import com.saber.sabernews.presentation.features.news.model.SourceView
import javax.inject.Inject

class SourceViewMapper @Inject constructor(
) : Mapper<SourceView, Source> {

    override fun mapToView(type: Source): SourceView {
        return SourceView(
            id = type.id,
            name = type.name,
        )
    }

}