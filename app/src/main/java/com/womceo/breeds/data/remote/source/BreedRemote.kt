package com.womceo.breeds.data.remote.source

import com.womceo.breeds.data.model.DogResponse
import com.womceo.breeds.data.model.RemoteBreedImages

interface BreedRemote {
    suspend fun getBreedList(): DogResponse
    suspend fun getBreedImages(breedName: String): RemoteBreedImages
}