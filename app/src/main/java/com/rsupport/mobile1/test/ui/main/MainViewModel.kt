package com.rsupport.mobile1.test.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.rsupport.mobile1.test.data.pagingsource.ImagePagingSource
import com.rsupport.mobile1.test.domain.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val imageRepository: ImageRepository
) : ViewModel() {

    val imagePagingDataFlow = Pager(
        config = PagingConfig(
            pageSize = 15,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { ImagePagingSource(imageRepository) }
    ).flow.cachedIn(viewModelScope)
}