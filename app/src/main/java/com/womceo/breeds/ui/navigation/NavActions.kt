package com.womceo.breeds.ui.navigation

import androidx.navigation.NavHostController
import com.womceo.breeds.ui.navigation.Constants.BREED_NAME

class NavActions(navHostController: NavHostController) {
    val photo: (String) -> Unit = { breedName ->
        navHostController.currentBackStackEntry?.savedStateHandle?.set(
            BREED_NAME,
            breedName
        )
        navHostController.navigate(BreedRoutes.Photo.path)
    }
}
