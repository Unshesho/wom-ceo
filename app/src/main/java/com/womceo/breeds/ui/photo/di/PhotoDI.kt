package com.womceo.breeds.ui.photo.di

import androidx.activity.ComponentActivity
import com.womceo.breeds.data.BreedsRepository
import com.womceo.breeds.data.remote.BreedRemoteImpl
import com.womceo.breeds.data.remote.retrofit.BreedsWebService
import com.womceo.breeds.data.remote.source.BreedRemote
import com.womceo.breeds.presentation.photos.PhotoViewModel
import com.womceo.breeds.presentation.photos.mapper.PhotoMapper
import com.womceo.breeds.ui.photo.PhotoIntentHandler
import com.womceo.network.retrofit.WebServiceFactory

class PhotoDI {
    fun getIntentHandler() = PhotoIntentHandler()

    fun getViewModel(activity: ComponentActivity): PhotoViewModel =
        getPhotoViewModel(
            activity = activity,
            mapper = getPhotoMapper(),
            repository = getRepository()
        )

    private fun getPhotoMapper() = PhotoMapper()

    private fun getRepository(): BreedsRepository = BreedsRepository(getRemoteImpl())

    private fun getRemoteImpl(): BreedRemote = BreedRemoteImpl(getApi())

    private fun getApi() =
        WebServiceFactory(BreedsWebService::class.java).createRemoteWebServiceConfig()
}