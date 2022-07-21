package com.rsupport.mobile1.test.activity.domain

import com.rsupport.mobile1.test.activity.data.GettyImageDataSource
import javax.inject.Inject

class GettyImageRepositoryImpl @Inject constructor(private val dataSource: GettyImageDataSource): GettyImageRepository {
    override suspend fun getImage(): List<GettyImageResponse>? =
        dataSource.requestGettyImage()?.map { it.mapper() }
}