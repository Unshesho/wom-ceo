package com.womceo.breeds.ui.list.di

import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.womceo.breeds.data.BreedsRepository
import com.womceo.breeds.presentation.list.mapper.ListMapper
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class ListViewModelFactory(
    private val mapper: ListMapper,
    private val repository: BreedsRepository,
    private val coroutineContext: CoroutineContext = Dispatchers.IO
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(
            ListMapper::class.java,
            BreedsRepository::class.java,
            CoroutineContext::class.java
        ).newInstance(mapper, repository, coroutineContext)
    }
}

inline fun <reified T : ViewModel> getListViewModel(
    activity: ComponentActivity,
    mapper: ListMapper,
    repository: BreedsRepository
): T {
    return ViewModelProvider(
        activity,
        ListViewModelFactory(mapper, repository)
    )[T::class.java]
}
