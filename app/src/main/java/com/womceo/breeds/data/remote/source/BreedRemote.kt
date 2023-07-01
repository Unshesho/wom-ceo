package com.womceo.breeds.data.remote.source

import com.womceo.breeds.data.model.RemoteDogResponse
import com.womceo.breeds.data.model.RemoteBreedImages

interface BreedRemote {
    suspend fun getBreedList(): RemoteDogResponse
    suspend fun getBreedImages(breedName: String): RemoteBreedImages
}