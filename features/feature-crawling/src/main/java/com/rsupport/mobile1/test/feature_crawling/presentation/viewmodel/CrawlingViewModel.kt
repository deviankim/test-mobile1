package com.rsupport.mobile1.test.feature_crawling.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rsupport.mobile1.test.core.ImageLoadState
import com.rsupport.mobile1.test.feature_crawling.domain.usecase.GetImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CrawlingViewModel @Inject constructor(
    private val getImageUseCase: GetImageUseCase
) : ViewModel() {
    private val _imageLoadState = MutableStateFlow<ImageLoadState>(ImageLoadState.Empty)
    val imageLoadState get() = _imageLoadState

    init {
        getImage(1)
    }

    fun getImage(page: Int) {
        viewModelScope.launch {
            _imageLoadState.value = ImageLoadState.Loading
            try {
                val images = getImageUseCase(page)
                if (images.isEmpty()) {
                    _imageLoadState.value = ImageLoadState.Empty
                } else {
                    _imageLoadState.value = ImageLoadState.Success(images)
                }
            } catch (e: Exception) {
                _imageLoadState.value = ImageLoadState.Failure(e)
            }
        }
    }
}