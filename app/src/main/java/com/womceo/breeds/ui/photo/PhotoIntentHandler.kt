package com.womceo.breeds.ui.photo

import com.womceo.breeds.presentation.photos.events.PhotoUIntent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.merge

class PhotoIntentHandler {
    private val userIntents = MutableSharedFlow<PhotoUIntent>()

    fun userIntents(breeName: String): Flow<PhotoUIntent> = merge(
        flow { emit(PhotoUIntent.SeeBreedPhotosUIntent(breeName)) },
        userIntents.asSharedFlow()
    )
}
