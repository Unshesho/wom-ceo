package com.womceo.breeds.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.womceo.breeds.data.BreedsRepository
import com.womceo.breeds.presentation.list.events.ListUIntent
import com.womceo.breeds.presentation.list.events.ListUIntent.InitialUIntent
import com.womceo.breeds.presentation.list.events.ListUiStates
import com.womceo.breeds.presentation.list.events.ListUiStates.DefaultLoadingUiState
import com.womceo.breeds.presentation.list.events.ListUiStates.DisplayListUiState
import com.womceo.breeds.presentation.list.events.ListUiStates.ErrorUiState
import com.womceo.breeds.presentation.list.mapper.ListMapper
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

class ListViewModel(
    private val mapper: ListMapper,
    private val repository: BreedsRepository,
    private val coroutineContext: CoroutineContext = Dispatchers.IO
) : ViewModel() {
    val defaultUiState = DefaultLoadingUiState
    private val uiState = MutableStateFlow<ListUiStates>(defaultUiState)

    fun processUserIntents(
        userIntents: Flow<ListUIntent>
    ): Flow<ListUiStates> =
        userIntents.buffer()
            .flatMapMerge { userIntent ->
                userIntent.handleUserIntent()
            }

    private fun ListUIntent.handleUserIntent(): Flow<ListUiStates> =
        when (this) {
            InitialUIntent -> getBreedList()
        }

    private fun getBreedList(): Flow<ListUiStates> =
        repository.getBreedList().map { response ->
            val breedList = with(mapper) { response.toPresentation() }
            DisplayListUiState(breedList = breedList.breeds) as ListUiStates
        }
            .onStart { emit(defaultUiState) }
            .catch { emit(ErrorUiState) }
            .flowOn(coroutineContext)

    fun uiState(): StateFlow<ListUiStates> = uiState
}
