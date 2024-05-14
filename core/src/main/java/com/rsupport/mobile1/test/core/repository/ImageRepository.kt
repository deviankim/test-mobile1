package com.rsupport.mobile1.test.core.repository

import com.rsupport.mobile1.test.core.model.ImageUiModel

interface ImageRepository {
    suspend fun getImage(page: Int): List<ImageUiModel>
}