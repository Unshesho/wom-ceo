package com.womceo.breeds.presentation.photos

import com.womceo.breeds.data.BreedsRepository
import com.womceo.breeds.data.model.RemoteBreedImages
import com.womceo.breeds.factory.BreedImagesFactory.makeBreedImages
import com.womceo.breeds.factory.BreedImagesFactory.makeRemoteBreedImages
import com.womceo.breeds.presentation.photos.events.PhotoUIntent.SeeBreedPhotosUIntent
import com.womceo.breeds.presentation.photos.events.PhotoUiStates
import com.womceo.breeds.presentation.photos.events.PhotoUiStates.DisplayBreedImages
import com.womceo.breeds.presentation.photos.events.PhotoUiStates.ErrorUiState
import com.womceo.breeds.presentation.photos.mapper.PhotoMapper
import com.womceo.breeds.presentation.photos.model.BreedImages
import com.womceo.utils.randomfactory.RandomFactory.generateRandomString
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test


internal class PhotoViewModelTest {
    private val mapper = mockk<PhotoMapper>()
    private val repository = mockk<BreedsRepository>()
    private val coroutineContext = Dispatchers.IO

    private val viewModel = PhotoViewModel(
        mapper = mapper,
        repository = repository,
        coroutineContext = coroutineContext
    )

    @Test
    fun `given SeeBreedPhotosUIntent when processUserIntents then emit DefaultLoadingUiState`() =
        runBlocking {
            val breedName = generateRandomString()
            val userIntent = SeeBreedPhotosUIntent(breedName)
            val remoteBreedImages = makeRemoteBreedImages(3)
            stubGetBreedImages(breedName,remoteBreedImages)

            viewModel.processUserIntents(
                userIntents = flow { emit(userIntent) },
                coroutineScope = this
            )

            val result = viewModel.uiState().first()

            Assert.assertTrue(result is PhotoUiStates.DefaultLoadingUiState)
        }

    @Test
    fun `given SeeBreedPhotosUIntent when processUserIntents then emit DisplayBreedImages`() =
        runBlocking {
            val breedName = generateRandomString()
            val userIntent = SeeBreedPhotosUIntent(breedName)
            val remoteBreedImages = makeRemoteBreedImages(3)
            val breedImages = makeBreedImages(3)
            stubGetBreedImages(breedName,remoteBreedImages)
            stubMapper(remoteBreedImages, breedImages)

            viewModel.processUserIntents(
                userIntents = flow { emit(userIntent) },
                coroutineScope = this
            )

            val result = viewModel.uiState().take(2).last()

            Assert.assertTrue(result is DisplayBreedImages)
        }

    @Test
    fun `given SeeBreedPhotosUIntent when processUserIntents then emit ErrorUiState`() =
        runBlocking {
            val breedName = generateRandomString()
            val userIntent = SeeBreedPhotosUIntent(breedName)
            val remoteBreedImages = makeRemoteBreedImages(3)
            stubGetBreedImages(breedName,remoteBreedImages)

            viewModel.processUserIntents(
                userIntents = flow { emit(userIntent) },
                coroutineScope = this
            )

            val result = viewModel.uiState().take(2).last()

            Assert.assertTrue(result is ErrorUiState)
        }

    private fun stubGetBreedImages(breedName: String, remote: RemoteBreedImages) {
        coEvery { repository.getBreedImages(breedName) } returns flow { emit(remote) }
    }

    private fun stubMapper(remote: RemoteBreedImages, presentation: BreedImages) {
        every { with(mapper) { remote.toPresentation() } } returns presentation
    }
}
