package com.saber.sabernews.presentation.features.news.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SourceView(
    val id: String? = null,
    val name: String? = null,
) : Parcelable