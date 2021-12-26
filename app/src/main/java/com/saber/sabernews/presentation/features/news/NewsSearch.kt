package com.saber.sabernews.presentation.features.news

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.saber.sabernews.R

@Composable
fun NewsSearch(
    onImeAction: (String) -> Unit = {}
) {
    val searchText = rememberSaveable { mutableStateOf("") }
    OutlinedTextField(
        value = searchText.value,
        onValueChange = {
            searchText.value = it
        },
        label = { Text(stringResource(R.string.search_for_news)) },
        modifier = Modifier
            .fillMaxWidth().padding(16.dp),
        textStyle = MaterialTheme.typography.body2,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                onImeAction(searchText.value)
            }
        )
    )
}