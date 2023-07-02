package com.womceo.breeds.ui.photo.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun PhotoCard(
    modifier: Modifier = Modifier,
    imageUrl: String
) {
    AsyncImage(
        modifier = modifier
            .size(120.dp)
            .padding(5.dp),
        model = imageUrl,
        contentDescription = null,
    )
}
