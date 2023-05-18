package com.example.crawlin_test.repository

import com.example.crawlin_test.data.model.ImageData
import com.example.crawlin_test.data.response.NetworkResult
import kotlinx.coroutines.flow.Flow

interface ImageRepository {
    suspend fun requestCrawlingImage() : Flow<NetworkResult<List<ImageData>>>
}