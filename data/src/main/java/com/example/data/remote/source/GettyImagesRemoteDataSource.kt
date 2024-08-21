package com.example.data.remote.source

import com.example.data.model.GettyImageDto

interface GettyImagesRemoteDataSource {

    suspend fun getGettyImages(page: Int): Result<List<GettyImageDto>>
}