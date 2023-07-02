package com.womceo.breeds.ui.navigation

import androidx.activity.ComponentActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.womceo.breeds.ui.list.di.ListDI
import com.womceo.breeds.ui.photo.di.PhotoDI

@ExperimentalAnimationApi
@Composable
fun NavGraph(activity: ComponentActivity, startDestination: String = BreedRoutes.List.path) {
    val navController = rememberAnimatedNavController()
    val navActions = remember() { NavActions(navController) }
    val listDependencies = ListDI()
    val photoDependencies = PhotoDI()
    val listViewModel = listDependencies.getListViewModel(activity)
    val photoViewModel = photoDependencies.getViewModel(activity)
    val listIntentHandler = listDependencies.getLisIntentHandler()
    val photoIntentHandler = photoDependencies.getIntentHandler()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        listNav(
            viewModel = listViewModel,
            intentHandler = listIntentHandler,
            navActions = navActions,
            navHostController = navController
        )
        photoNav(
            viewModel = photoViewModel,
            intentHandler = photoIntentHandler,
            navHostController = navController
        )
    }
}
