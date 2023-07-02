package com.womceo.breeds.data.factory

import com.womceo.breeds.data.model.RemoteDogResponse
import com.womceo.breeds.presentation.list.model.DogResponse
import com.womceo.utils.randomfactory.RandomFactory.generateRandomString

object DogResponseFactory {
    private fun makeBreedsList(count: Int) = (0..count).map { generateRandomString() }

    private fun makeMapOfMessage() =
        mapOf(
            generateRandomString() to listOf(generateRandomString()),
            generateRandomString() to listOf()
        )

    fun makeRemoteDogResponse() = RemoteDogResponse(
        message = makeMapOfMessage(),
        status = generateRandomString()
    )

    fun makeDogResponse(count: Int) = DogResponse(
        breeds = makeBreedsList(count)
    )
}