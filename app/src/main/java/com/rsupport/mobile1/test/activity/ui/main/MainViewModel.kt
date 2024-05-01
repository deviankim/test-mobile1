package com.rsupport.mobile1.test.activity.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.rsupport.mobile1.test.activity.data.model.ImageInformation
import com.rsupport.mobile1.test.activity.data.repository.MainRepository
import com.rsupport.mobile1.test.activity.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(repository: MainRepository) : ViewModel() {

    val items: Flow<PagingData<ImageInformation>> = repository
        .getImageList(Constants.PAGE_SIZE)
        .cachedIn(viewModelScope)
}