package com.womceo.breeds.ui.photo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import com.womceo.breeds.presentation.photos.events.PhotoUiStates
import com.womceo.breeds.presentation.photos.events.PhotoUiStates.DefaultLoadingUiState
import com.womceo.breeds.presentation.photos.events.PhotoUiStates.DisplayBreedImages
import com.womceo.breeds.presentation.photos.events.PhotoUiStates.ErrorUiState
import com.womceo.breeds.ui.commons.ErrorScreen
import com.womceo.breeds.ui.commons.LoadingScreen
import com.womceo.breeds.ui.photo.components.PhotoCard

@Composable
fun PhotoScreen(
    modifier: Modifier = Modifier,
    uiStates: State<PhotoUiStates>,
) {
    when (val currentUiStates = uiStates.value) {
        DefaultLoadingUiState -> LoadingScreen()
        is DisplayBreedImages -> PhotoContent(
            modifier = modifier,
            breedImages = currentUiStates.breedImages.breedImages
        )

        ErrorUiState -> ErrorScreen()
    }
}

@Composable
fun PhotoContent(
    modifier: Modifier,
    breedImages: List<String>
) {
    Column(modifier.fillMaxSize()) {
        LazyColumn(content = {
            items(breedImages) { breedImage ->
                PhotoCard(imageUrl = breedImage)
            }
        })
    }
}
