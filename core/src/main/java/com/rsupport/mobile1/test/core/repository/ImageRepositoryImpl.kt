package com.rsupport.mobile1.test.core.repository

import com.rsupport.mobile1.test.network_api.datasource.ImageDataSource
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val imageDataSource: ImageDataSource
) : ImageRepository{
    override suspend fun getImage(page: Int): List<String> {
        return imageDataSource.getImage(page)
    }
}