package com.womceo.breeds.presentation.photos.mapper

import com.womceo.breeds.data.model.RemoteBreedImages
import com.womceo.breeds.presentation.photos.model.BreedImages

class PhotoMapper {
    fun RemoteBreedImages.toPresentation() = BreedImages(
        breedImages = breedImages.orEmpty()
    )
}
