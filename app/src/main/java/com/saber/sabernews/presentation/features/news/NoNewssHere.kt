package com.saber.sabernews.presentation.features.news

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.saber.sabernews.R

@Composable
fun NoNewsHere(
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Card(
            shape = MaterialTheme.shapes.large.copy(CornerSize(10.dp)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            elevation = 4.dp,
        ) {
            Box(modifier = Modifier.background(MaterialTheme.colors.surface)) {
                Column(
                    modifier = Modifier
                        .padding(36.dp)
                        .background(MaterialTheme.colors.surface)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_no_search),
                        contentDescription = "Empty Table",
                        modifier = Modifier
                            .width(212.dp)
                            .height(140.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(36.dp))

                    Text(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = stringResource(R.string.news_no_data),
                        style = MaterialTheme.typography.h5,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )

                }
            }
        }
    }
}

@Preview
@Composable
fun NoNewsHerePreview() {
    NoNewsHere()
}