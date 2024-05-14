package com.rsupport.mobile1.test.core

sealed class ImageLoadState {
    data object Loading : ImageLoadState()
    data class Success(val images: List<String>) : ImageLoadState()
    data class Failure(val error: Exception) : ImageLoadState()
    data object Empty : ImageLoadState()
}