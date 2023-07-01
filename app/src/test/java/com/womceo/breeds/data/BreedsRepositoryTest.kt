package com.womceo.breeds.data

import com.womceo.breeds.data.factory.BreedImagesFactory.makeRemoteBreedImages
import com.womceo.breeds.data.factory.DogResponseFactory.makeRemoteDogResponse
import com.womceo.breeds.data.model.RemoteBreedImages
import com.womceo.breeds.data.model.RemoteDogResponse
import com.womceo.breeds.data.remote.source.BreedRemote
import com.womceo.utils.randomfactory.RandomFactory.generateRandomString
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

internal class BreedsRepositoryTest{
    private val remote = mockk<BreedRemote>()
    private val repository = BreedsRepository(remote)

    @Test
    fun `when call getBreedList then emit RemoteDogResponse`() = runBlocking {
        val remoteDogRemote = makeRemoteDogResponse()
        stubGetBreedList(remoteDogRemote)

        val result = repository.getBreedList().first()

        assertEquals(remoteDogRemote, result)

    }

    @Test
    fun `given breedName when call getBreedImages then emit RemoteBreedImages`() = runBlocking {
        val breedName = generateRandomString()
        val bredImages = makeRemoteBreedImages(3)

        stubGetBreedImages(breedName, bredImages)

        val result = repository.getBreedImages(breedName).first()

        assertEquals(bredImages,result )

    }

    private fun stubGetBreedList(response: RemoteDogResponse) {
        coEvery { remote.getBreedList() } returns response
    }
    private fun stubGetBreedImages(breedName: String, response: RemoteBreedImages){
        coEvery { remote.getBreedImages(breedName) } returns response
    }
}
