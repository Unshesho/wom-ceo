package com.womceo.breeds.factory

import com.womceo.breeds.data.model.RemoteBreedImages
import com.womceo.breeds.presentation.photos.model.BreedImages
import com.womceo.utils.randomfactory.RandomFactory.generateRandomString

object BreedImagesFactory {
    fun makeRemoteBreedImages(count: Int) = RemoteBreedImages(
        breedImages = makeBreedList(count)
    )

    private fun makeBreedList(count: Int) = (0..count).map { generateRandomString() }

    fun makeBreedImages(count: Int) = BreedImages(
        breedImages = makeBreedList(count)
    )
}
