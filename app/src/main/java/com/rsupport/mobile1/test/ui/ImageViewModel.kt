package com.rsupport.mobile1.test.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.rsupport.mobile1.test.data.ImageRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

class ImageViewModel : ViewModel() {
    private val repository = ImageRepository()
    private val queryFlow = MutableStateFlow("")

    val pagingDataFlow = queryFlow
        .flatMapLatest {
            repository.getImageSearch()
        }
        .cachedIn(viewModelScope)

    fun handleQuery() {
        queryFlow.value
    }
}