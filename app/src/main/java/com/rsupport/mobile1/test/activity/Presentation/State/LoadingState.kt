package com.rsupport.mobile1.test.activity.Presentation.State

import com.rsupport.mobile1.test.activity.Data.Entity.ImageEntity
import com.rsupport.mobile1.test.activity.Data.Entity.ImagesEntity

sealed class LoadingState{

    object UnInitialized : LoadingState()
    object Loading : LoadingState()

    data class Success(
        val Loaded : List<ImageEntity>
    ) : LoadingState()

    object Error : LoadingState()
}
