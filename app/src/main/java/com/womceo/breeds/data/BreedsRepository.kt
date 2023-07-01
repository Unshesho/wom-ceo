package com.womceo.breeds.data

import com.womceo.breeds.data.model.RemoteDogResponse
import com.womceo.breeds.data.model.RemoteBreedImages
import com.womceo.breeds.data.remote.source.BreedRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BreedsRepository(
    private val remote: BreedRemote
) {

    fun getBreedList(): Flow<RemoteDogResponse> = flow {
        val response = remote.getBreedList()
        emit(response)
    }

    fun getBreedImages(breedName: String): Flow<RemoteBreedImages> = flow {
        val breedImages = remote.getBreedImages(breedName)
        emit(breedImages)
    }
}
