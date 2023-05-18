package com.example.crawlin_test.repository

import com.example.crawlin_test.data.model.ImageData
import com.example.crawlin_test.data.remote.RemoteImageDataSource
import com.example.crawlin_test.data.response.NetworkResult
import com.example.crawlin_test.data.response.safeFlow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImageRepositorySource @Inject constructor (private val remoteRepository: RemoteImageDataSource) : ImageRepository {

    override suspend fun requestCrawlingImage(): Flow<NetworkResult<List<ImageData>>> = safeFlow {
        remoteRepository.getImg()
    }

}