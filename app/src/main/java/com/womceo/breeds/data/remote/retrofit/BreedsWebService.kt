package com.womceo.breeds.data.remote.retrofit

import com.womceo.breeds.data.model.RemoteDogResponse
import com.womceo.breeds.data.model.RemoteBreedImages
import retrofit2.http.GET
import retrofit2.http.Path

interface BreedsWebService {
    @GET("breeds/list/all")
    suspend fun getBreedList(): RemoteDogResponse

    @GET("breed/{breed_name}/images")
    suspend fun getBreedImages(
        @Path("breed_name") breedName: String
    ): RemoteBreedImages
}
