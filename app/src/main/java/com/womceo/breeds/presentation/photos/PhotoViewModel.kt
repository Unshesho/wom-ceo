package com.womceo.breeds.presentation.photos

import com.womceo.breeds.data.BreedsRepository
import com.womceo.breeds.presentation.photos.events.PhotoUIntent
import com.womceo.breeds.presentation.photos.events.PhotoUIntent.SeeBreedPhotosUIntent
import com.womceo.breeds.presentation.photos.events.PhotoUiStates
import com.womceo.breeds.presentation.photos.events.PhotoUiStates.DefaultLoadingUiState
import com.womceo.breeds.presentation.photos.events.PhotoUiStates.DisplayBreedImages
import com.womceo.breeds.presentation.photos.events.PhotoUiStates.ErrorUiState
import com.womceo.breeds.presentation.photos.mapper.PhotoMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlin.coroutines.CoroutineContext

class PhotoViewModel(
    private val mapper: PhotoMapper,
    private val repository: BreedsRepository,
    private val coroutineContext: CoroutineContext = Dispatchers.IO
) {
    private val defaultUiState = DefaultLoadingUiState
    private val uiState = MutableStateFlow<PhotoUiStates>(defaultUiState)

    fun processUserIntents(
        userIntents: Flow<PhotoUIntent>,
        coroutineScope: CoroutineScope
    ) {
        userIntents.buffer()
            .flatMapMerge { userIntent ->
                userIntent.handleUserIntent()
            }
            .onEach {
                uiState.value = it
            }
            .launchIn(coroutineScope)
    }

    private fun PhotoUIntent.handleUserIntent(): Flow<PhotoUiStates> =
        when (this) {
            is SeeBreedPhotosUIntent -> getBreedImages(breedName)
        }

    private fun getBreedImages(breedName: String): Flow<PhotoUiStates> =
        repository.getBreedImages(breedName).map { response ->
            val breedImages = with(mapper) { response.toPresentation() }
           DisplayBreedImages(breedImages = breedImages) as PhotoUiStates
        }
            .onStart { emit(defaultUiState) }
            .catch { emit(ErrorUiState) }
            .flowOn(coroutineContext)

    fun uiState(): StateFlow<PhotoUiStates> = uiState
}
