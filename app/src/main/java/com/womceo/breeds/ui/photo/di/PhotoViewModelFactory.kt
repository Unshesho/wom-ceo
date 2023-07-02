package com.womceo.breeds.ui.photo.di

import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.womceo.breeds.data.BreedsRepository
import com.womceo.breeds.presentation.list.mapper.ListMapper
import com.womceo.breeds.presentation.photos.mapper.PhotoMapper
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class PhotoViewModelFactory(
    private val mapper: PhotoMapper,
    private val repository: BreedsRepository,
    private val coroutineContext: CoroutineContext = Dispatchers.IO
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(
            PhotoMapper::class.java,
            BreedsRepository::class.java,
            CoroutineContext::class.java
        ).newInstance(mapper, repository, coroutineContext)
    }
}

inline fun <reified T : ViewModel> getPhotoViewModel(
    activity: ComponentActivity,
    mapper: PhotoMapper,
    repository: BreedsRepository
): T {
    return ViewModelProvider(
        activity,
        PhotoViewModelFactory(mapper, repository)
    )[T::class.java]
}