package com.womceo.breeds.presentation.list.events

sealed class ListUiStates {
    object DefaultLoadingUiState: ListUiStates()
    data class DisplayListUiState(val breedList: List<String>): ListUiStates()
    object ErrorUiState: ListUiStates()
}
