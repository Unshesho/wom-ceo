package com.womceo.breeds.data.remote

import com.womceo.breeds.factory.BreedImagesFactory.makeRemoteBreedImages
import com.womceo.breeds.factory.DogResponseFactory.makeRemoteDogResponse
import com.womceo.breeds.data.model.RemoteBreedImages
import com.womceo.breeds.data.model.RemoteDogResponse
import com.womceo.breeds.data.remote.retrofit.BreedsWebService
import com.womceo.utils.randomfactory.RandomFactory.generateRandomString
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

internal class BreedRemoteImplTest {
    private val api = mockk<BreedsWebService>()
    private val remote = BreedRemoteImpl(api)

    @Test
    fun `when call getBreedList then return RemoteDogResponse`() = runBlocking {
        val remoteDogResponse = makeRemoteDogResponse()
        stubGetBreedList(remoteDogResponse)

        remote.getBreedList()

        coVerify { api.getBreedList() }
    }

    @Test
    fun `given breedName when call getBreedImages then return RemoteBreedImages`() = runBlocking {
        val breedName = generateRandomString()
        val remoteBreedImages = makeRemoteBreedImages(3)
        stubGetBreedImages(breedName, remoteBreedImages)

        remote.getBreedImages(breedName)

        coVerify { api.getBreedImages(breedName) }
    }

    private fun stubGetBreedList(response: RemoteDogResponse) {
        coEvery { api.getBreedList() } returns response
    }

    private fun stubGetBreedImages(breedName: String, response: RemoteBreedImages) {
        coEvery { api.getBreedImages(breedName) } returns response
    }
}
