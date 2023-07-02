package com.womceo.breeds.ui.list

import com.womceo.breeds.presentation.list.events.ListUIntent
import com.womceo.breeds.presentation.list.events.ListUIntent.InitialUIntent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.merge

class ListIntentHandler {
    private val userIntents = MutableSharedFlow<ListUIntent>()

    fun userIntents(): Flow<ListUIntent> = merge(
        flow { emit(InitialUIntent) },
        userIntents.asSharedFlow()
    )
}
