package com.womceo.breeds.presentation.photos.events

import com.womceo.breeds.presentation.photos.model.BreedImages

sealed class PhotoUiStates {
    object DefaultLoadingUiState: PhotoUiStates()
    data class DisplayBreedImages(val breedImages: BreedImages) : PhotoUiStates()
    object ErrorUiState: PhotoUiStates()
}
