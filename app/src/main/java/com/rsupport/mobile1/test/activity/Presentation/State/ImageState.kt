package com.rsupport.mobile1.test.activity.Presentation.State

import com.rsupport.mobile1.test.activity.Data.Entity.ImageEntity

sealed class ImageState{
    object UnInitialized : ImageState()

    object Loading : ImageState()

    data class Success(
        val Images : List<ImageEntity>
    ) : ImageState()

    object Error : ImageState()
}
