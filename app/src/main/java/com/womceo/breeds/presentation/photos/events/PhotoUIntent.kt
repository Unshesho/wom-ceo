package com.womceo.breeds.presentation.photos.events


sealed class PhotoUIntent {
    data class SeeBreedPhotosUIntent(val breedName: String) : PhotoUIntent()
}
