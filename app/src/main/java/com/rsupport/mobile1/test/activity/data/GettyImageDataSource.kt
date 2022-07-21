package com.rsupport.mobile1.test.activity.data


interface GettyImageDataSource {
    suspend fun requestGettyImage(): List<GettyImage>?
}