package com.saber.sabernews.presentation.features.news

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.saber.sabernews.presentation.features.news.model.NewsView

@Composable
fun NewsScreen(
    loading: Boolean,
    pageNews: Int,
    news: List<NewsView>,
    onChangeNewsScrollPosition: (Int) -> Unit,
    onTriggerEvent: (NewsListEvent) -> Unit,
    onImeAction: (String) -> Unit = {},
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.surface)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 30.dp))
                .background(color = Color(0xFFF8FAFA))
        ) {
            if (loading && news.isEmpty()) LoadingNewsListShimmer(imageHeight = 200.dp)
            else {
                Column() {
                    NewsSearch(onImeAction)
                    if (news.isEmpty()) NoNewsHere()
                    else {
                        NewsList(
                            news, onChangeNewsScrollPosition,
                            pageNews, loading, onTriggerEvent
                        )
                    }
                }

            }
        }
    }
}