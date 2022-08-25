package com.rsupport.mobile1.test.viewlayer.gallery

import com.rsupport.mobile1.test.model.ui.GalleryItem

sealed class GalleryUiState(val mask: Int) {

    companion object {
        const val MASK_LOADING = 1 shl 0
        const val MASK_FAILURE = 1 shl 1
        const val MASK_SUCCESS = 1 shl 2
    }

    object Loading : GalleryUiState(MASK_LOADING)

    object Failure : GalleryUiState(MASK_FAILURE)

    data class Success(
        val items: List<GalleryItem>? = null
    ) : GalleryUiState(MASK_SUCCESS)

}