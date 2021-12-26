package com.saber.sabernews.presentation.features.news

import com.saber.sabernews.presentation.features.news.model.NewsView

sealed class NewsListEvent {

    object FirstPageEvent : NewsListEvent()

    object NextPageEvent : NewsListEvent()

    // restore after process death
    object RestoreStateEvent: NewsListEvent()

    data class OpenNewsDetails(val news: NewsView) : NewsListEvent()
}