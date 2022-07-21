package com.rsupport.mobile1.test.activity.domain

import com.rsupport.mobile1.test.activity.data.GettyImage
import com.rsupport.mobile1.test.activity.data.GettyImageWebImpl

class GettyImageRepositoryImpl(private val web: GettyImageWebImpl): GettyImageRepository {

    override suspend fun getImage(): List<GettyImage>? {
        return web.requestGettyImage()
    }
}