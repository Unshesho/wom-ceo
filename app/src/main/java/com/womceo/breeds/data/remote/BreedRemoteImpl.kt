package com.womceo.breeds.data.remote

import com.womceo.breeds.data.model.RemoteDogResponse
import com.womceo.breeds.data.model.RemoteBreedImages
import com.womceo.breeds.data.remote.retrofit.BreedsWebService
import com.womceo.breeds.data.remote.source.BreedRemote

class BreedRemoteImpl(
    private val api: BreedsWebService
) : BreedRemote {
    override suspend fun getBreedList(): RemoteDogResponse =
        api.getBreedList()

    override suspend fun getBreedImages(breedName: String): RemoteBreedImages =
        api.getBreedImages(breedName)
}
