package com.rsupport.mobile1.test.feature_crawling.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rsupport.mobile1.test.feature_crawling.domain.usecase.GetImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CrawlingViewModel @Inject constructor(
    private val getImageUseCase: GetImageUseCase
) : ViewModel() {

    private val _imageUrlList = MutableStateFlow<List<String>>(emptyList())
    val imageUrlList: StateFlow<List<String>> = _imageUrlList

    init {
        getImage(1)
    }

    fun getImage(page: Int) {
        viewModelScope.launch {
            val images = getImageUseCase(page)
            _imageUrlList.value = images
        }
    }
}