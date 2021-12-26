package com.saber.sabernews.presentation.features.news

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.saber.sabernews.presentation.features.news.model.NewsView

@Composable
fun NewsList(
    news: List<NewsView>, onChangeNewsScrollPosition: (Int) -> Unit,
    pageNews: Int,
    loading: Boolean,
    onTriggerEvent: (NewsListEvent) -> Unit
) {
    Column(
        modifier = Modifier.padding(
            top = 12.dp,
            start = 16.dp,
            end = 16.dp
        )
    ) {
        LazyColumn(

        ) {
            itemsIndexed(
                items = news
            ) { index, news ->
                onChangeNewsScrollPosition(index)
                if ((index + 1) >= (pageNews * PAGE_SIZE) && !loading) {
                    onTriggerEvent(NewsListEvent.NextPageEvent)
                }
                NewsCard(
                    news = news,
                ) {
                    onTriggerEvent(
                        NewsListEvent.OpenNewsDetails(news)
                    )
                }
            }
        }
    }
}