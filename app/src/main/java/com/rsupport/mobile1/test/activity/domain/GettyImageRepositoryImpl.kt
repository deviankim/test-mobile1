package com.rsupport.mobile1.test.activity.domain

import com.rsupport.mobile1.test.activity.data.GettyImageWebImpl

class GettyImageRepositoryImpl: GettyImageRepository {

    override fun getImage(): List<GettyImageResponse> {
        GettyImageWebImpl().requestGettyImage()
        return listOf()
    }
}