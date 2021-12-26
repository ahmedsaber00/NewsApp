package com.saber.sabernews.presentation.features.news

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.saber.sabernews.extensions.loadPicture
import com.saber.sabernews.presentation.features.news.model.NewsView

@Composable
fun NewsCard(
    news: NewsView,
    onClick: () -> Unit
) {
    Card(
        shape = MaterialTheme.shapes.large.copy(CornerSize(20.dp)),
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = 4.dp,
    ) {
        Box(modifier = Modifier.background(MaterialTheme.colors.surface)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
                    .background(color = MaterialTheme.colors.surface)
            ) {
                val logo = loadPicture(url = news.urlToImage).value
                logo?.let { img ->
                    Image(
                        bitmap = img.asImageBitmap(),
                        contentDescription = "Shop Featured Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .background(Color.White, RoundedCornerShape(10.dp))
                            .clip(RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.Crop,
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = news.title.toString(),
                    style = MaterialTheme.typography.h6,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = news.content.toString(),
                    style = MaterialTheme.typography.subtitle1,
                    color = Color.DarkGray,
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    Modifier
                        .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = news.author.toString(),
                        color = Color.Blue,
                    )
                    Text(
                        text = news.source?.name.toString(),
                        color = Color.Red,
                    )

                }
            }
        }
    }
}

@Preview
@Composable
fun NewsCardPreview() {
    NewsCard(NewsView()) {}
}

@Preview
@Composable
fun NewsCardPreviewRTL() {
    val layoutDirection = LayoutDirection.Rtl
    CompositionLocalProvider(LocalLayoutDirection provides layoutDirection) {
        NewsCard(NewsView()) {}
    }
}