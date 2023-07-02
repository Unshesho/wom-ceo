package com.womceo.breeds.ui.navigation

sealed class BreedRoutes(val path: String) {
    object Photo : BreedRoutes("Photo")
    object List : BreedRoutes("List")
}
