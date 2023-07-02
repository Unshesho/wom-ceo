package com.womceo.breeds.presentation.list

import com.womceo.breeds.data.BreedsRepository
import com.womceo.breeds.factory.DogResponseFactory.makeDogResponse
import com.womceo.breeds.factory.DogResponseFactory.makeRemoteDogResponse
import com.womceo.breeds.data.model.RemoteDogResponse
import com.womceo.breeds.presentation.list.events.ListUIntent.InitialUIntent
import com.womceo.breeds.presentation.list.events.ListUiStates.DefaultLoadingUiState
import com.womceo.breeds.presentation.list.events.ListUiStates.DisplayListUiState
import com.womceo.breeds.presentation.list.events.ListUiStates.ErrorUiState
import com.womceo.breeds.presentation.list.mapper.ListMapper
import com.womceo.breeds.presentation.list.model.DogResponse
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Test

internal class ListViewModelTest {
    private val mapper = mockk<ListMapper>()
    private val repository = mockk<BreedsRepository>()
    private val coroutineContext = Dispatchers.IO

    private val viewModel = ListViewModel(
        mapper = mapper,
        repository = repository,
        coroutineContext = coroutineContext
    )

    @Test
    fun `given InitialUserIntent when processUserIntents then emit DefaultLoadingUiState`() =
        runBlocking {
            val userIntent = InitialUIntent
            val remoteDogResponse = makeRemoteDogResponse()
            stubGetBreedList(remoteDogResponse)

            val result = viewModel.processUserIntents(
                userIntents = flow { emit(userIntent) }
            )

            assertTrue(result.first() is DefaultLoadingUiState)
        }

    @Test
    fun `given InitialUserIntent when processUserIntents then emit DisplayListUiState`() =
        runBlocking {
            val userIntent = InitialUIntent
            val remoteDogResponse = makeRemoteDogResponse()
            val dogResponse = makeDogResponse(3)
            stubGetBreedList(remoteDogResponse)
            stubMapper(remoteDogResponse, dogResponse)

            val result = viewModel.processUserIntents(
                userIntents = flow { emit(userIntent) }
            )

            assertTrue(result.take(2).last() is DisplayListUiState)
        }

    @Test
    fun `given error when processUserIntents then emit ErrorUiState`() = runBlocking {
        val userIntent = InitialUIntent
        val remoteDogResponse = makeRemoteDogResponse()
        stubGetBreedList(remoteDogResponse)

        val result = viewModel.processUserIntents(
            userIntents = flow { emit(userIntent) }
        )
        assertTrue(result.take(2).last() is ErrorUiState)
    }

    private fun stubGetBreedList(remote: RemoteDogResponse) {
        coEvery { repository.getBreedList() } returns flow { emit(remote) }
    }

    private fun stubMapper(remote: RemoteDogResponse, presentation: DogResponse) {
        every { with(mapper) { remote.toPresentation() } } returns presentation
    }
}