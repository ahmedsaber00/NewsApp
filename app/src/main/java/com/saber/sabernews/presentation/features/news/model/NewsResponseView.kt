package com.saber.sabernews.presentation.features.news.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsResponseView(
    val state: String? = null,
    val totalResults: Int? = null,
    val articles: List<NewsView>? = null
) : Parcelable