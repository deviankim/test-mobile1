package com.rsupport.mobile1.test.activity.domain

import com.rsupport.mobile1.test.activity.data.GettyImageDataSourceImpl

class GettyImageRepositoryImpl(private val web: GettyImageDataSourceImpl): GettyImageRepository {

    override suspend fun getImage(): List<GettyImageResponse>? {
        return web.requestGettyImage()?.map { it.mapper() }
    }
}