package com.saber.sabernews.presentation.features.news

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerNewsCardItem(
    colors: List<Color>,
    xShimmer: Float,
    yShimmer: Float,
    cardHeight: Dp,
    gradientWidth: Float,
    padding: Dp
) {
    val brush = linearGradient(
        colors,
        start = Offset(xShimmer - gradientWidth, yShimmer - gradientWidth),
        end = Offset(xShimmer, yShimmer)
    )
    Column(modifier = Modifier.padding(start = padding, end = padding, top = 4.dp, bottom = 4.dp)) {
        Card(
            shape = MaterialTheme.shapes.large.copy(CornerSize(20.dp)),
            modifier = Modifier.fillMaxWidth().size(cardHeight),
            elevation = 4.dp
        ) {
            Surface(shape = MaterialTheme.shapes.small) {
                Spacer(modifier = Modifier.fillMaxSize().background(brush = brush))
            }
        }
    }
}