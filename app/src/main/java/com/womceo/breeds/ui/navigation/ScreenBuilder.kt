package com.womceo.breeds.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.womceo.breeds.presentation.list.ListViewModel
import com.womceo.breeds.presentation.photos.PhotoViewModel
import com.womceo.breeds.ui.list.ListIntentHandler
import com.womceo.breeds.ui.list.ListScreen
import com.womceo.breeds.ui.navigation.Constants.BREED_NAME
import com.womceo.breeds.ui.photo.PhotoIntentHandler
import com.womceo.breeds.ui.photo.PhotoScreen

fun NavGraphBuilder.listNav(
    viewModel: ListViewModel,
    intentHandler: ListIntentHandler,
    navActions: NavActions,
    navHostController: NavHostController
) = composable(
    route = BreedRoutes.List.path,
) {
    val listUiStates = remember {
        viewModel.processUserIntents(
            userIntents = intentHandler.userIntents()
        )
    }.collectAsState(initial = viewModel.defaultUiState)
    ListScreen(
        uiStates = listUiStates,
        navActions = navActions,
        navHostController = navHostController
    )
}

fun NavGraphBuilder.photoNav(
    viewModel: PhotoViewModel,
    intentHandler: PhotoIntentHandler,
    navHostController: NavHostController
) = composable(
    route = BreedRoutes.Photo.path,
) {
    navHostController.previousBackStackEntry?.savedStateHandle?.get<String>(
        BREED_NAME
    )?.let {
        val photoUiState = remember {
            viewModel.processUserIntents(
                userIntents = intentHandler.userIntents(it)
            )
        }.collectAsState(initial = viewModel.defaultUiState)
        PhotoScreen(
            uiStates = photoUiState
        )
    }
}
