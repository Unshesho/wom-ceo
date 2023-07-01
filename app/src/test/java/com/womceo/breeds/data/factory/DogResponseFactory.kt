package com.womceo.breeds.data.factory

import com.womceo.breeds.data.model.RemoteDogResponse
import com.womceo.utils.randomfactory.RandomFactory.generateRandomString

object DogResponseFactory {
    fun makeRemoteDogResponse() = RemoteDogResponse(
        message = makeMapOfMessage(),
        status = generateRandomString()
    )

    private fun makeMapOfMessage() =
        mapOf(
            generateRandomString() to listOf(generateRandomString()),
            generateRandomString() to listOf()
        )

}