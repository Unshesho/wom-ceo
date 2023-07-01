package com.womceo.breeds.data.factory

import com.womceo.breeds.data.model.RemoteBreedImages
import com.womceo.utils.randomfactory.RandomFactory.generateRandomString

object BreedImagesFactory {
    fun makeRemoteBreedImages(count: Int) = RemoteBreedImages(
        breedList = makeBreedList(count)
    )

    private fun makeBreedList(count: Int) = (0..count).map { generateRandomString() }
}
