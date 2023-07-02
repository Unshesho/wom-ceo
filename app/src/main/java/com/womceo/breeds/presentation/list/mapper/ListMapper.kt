package com.womceo.breeds.presentation.list.mapper

import com.womceo.breeds.data.model.RemoteDogResponse
import com.womceo.breeds.presentation.list.model.DogResponse

class ListMapper {
    fun RemoteDogResponse.toPresentation() = DogResponse(
        breeds = mutableListOf<String>().apply {
            for ((key, list) in message.orEmpty()) {
                add(key)
                addAll(list)
            }
        }
    )
}
