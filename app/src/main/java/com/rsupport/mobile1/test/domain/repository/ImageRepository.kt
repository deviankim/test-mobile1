package com.rsupport.mobile1.test.domain.repository

import com.rsupport.mobile1.test.domain.model.Image

interface ImageRepository {
    suspend fun getImageList(page: Int) : Result<List<Image>>
}