package com.rsupport.mobile1.test.activity.domain

import com.rsupport.mobile1.test.activity.data.GettyImageWebImpl

class GettyImageRepositoryImpl(private val web: GettyImageWebImpl): GettyImageRepository {

    override suspend fun getImage(): List<GettyImageResponse>? {
        return web.requestGettyImage()?.map { it.mapper() }
    }
}