package com.womceo.breeds.ui.list.di

import androidx.activity.ComponentActivity
import com.womceo.breeds.data.BreedsRepository
import com.womceo.breeds.data.remote.BreedRemoteImpl
import com.womceo.breeds.data.remote.retrofit.BreedsWebService
import com.womceo.breeds.data.remote.source.BreedRemote
import com.womceo.breeds.presentation.list.ListViewModel
import com.womceo.breeds.presentation.list.mapper.ListMapper
import com.womceo.breeds.ui.list.ListIntentHandler
import com.womceo.network.retrofit.WebServiceFactory

class ListDI {
    fun getLisIntentHandler() = ListIntentHandler()

    fun getListViewModel(activity: ComponentActivity): ListViewModel =
        getListViewModel(activity = activity, mapper = getListMapper(), repository = getRepository())

    private fun getListMapper() = ListMapper()

    private fun getRepository(): BreedsRepository = BreedsRepository(getRemoteImpl())

    private fun getRemoteImpl(): BreedRemote = BreedRemoteImpl(getApi())

    private fun getApi() =
        WebServiceFactory(BreedsWebService::class.java).createRemoteWebServiceConfig()
}