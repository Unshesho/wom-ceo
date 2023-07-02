package com.womceo.breeds.ui.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.womceo.breeds.presentation.list.events.ListUiStates
import com.womceo.breeds.presentation.list.events.ListUiStates.DisplayListUiState
import com.womceo.breeds.presentation.list.events.ListUiStates.ErrorUiState
import com.womceo.breeds.ui.commons.ErrorScreen
import com.womceo.breeds.ui.commons.LoadingScreen
import com.womceo.breeds.ui.list.components.ListButton
import com.womceo.breeds.ui.navigation.NavActions

@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    uiStates: State<ListUiStates>,
    navActions: NavActions,
    navHostController: NavHostController
) {
    when (val currentUiStates = uiStates.value) {
        ListUiStates.DefaultLoadingUiState -> LoadingScreen()
        is DisplayListUiState -> ListContent(
            modifier = modifier,
            breedNames = currentUiStates.breedList,
            navActions = navActions,
            navController = navHostController
        )

        ErrorUiState -> ErrorScreen()
    }
}

@Composable
fun ListContent(
    modifier: Modifier,
    breedNames: List<String>,
    navActions: NavActions,
    navController: NavHostController
) {
    Column(modifier.fillMaxSize()) {
        LazyColumn(content = {
            items(breedNames) { breedName ->
                ListButton(breedName = breedName, onClick = {
                    navActions.photo(breedName)
                })
            }
        })
    }
}
