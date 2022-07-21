package com.rsupport.mobile1.test.activity.domain

import com.rsupport.mobile1.test.activity.data.GettyImageWebImpl

class GettyImageRepositoryImpl(private val web: GettyImageWebImpl): GettyImageRepository {

    override fun getImage(): List<GettyImageResponse> {
        web.requestGettyImage()
        return listOf()
    }
}