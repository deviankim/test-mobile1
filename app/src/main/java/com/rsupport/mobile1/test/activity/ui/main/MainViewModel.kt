package com.rsupport.mobile1.test.activity.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.rsupport.mobile1.test.activity.data.model.ImageInformation
import com.rsupport.mobile1.test.activity.data.repository.MainRepository
import com.rsupport.mobile1.test.activity.util.Constants
import kotlinx.coroutines.flow.Flow

class MainViewModel(repository: MainRepository) : ViewModel() {

    val items: Flow<PagingData<ImageInformation>> = repository
        .getImageList(Constants.PAGE_SIZE)
        .cachedIn(viewModelScope)

    companion object {
        fun provideFactory(repository: MainRepository) = viewModelFactory {
            initializer {
                MainViewModel(repository)
            }
        }
    }
}