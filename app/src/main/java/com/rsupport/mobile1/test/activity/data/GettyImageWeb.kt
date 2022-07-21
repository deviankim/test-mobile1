package com.rsupport.mobile1.test.activity.data


interface GettyImageWeb {
    suspend fun requestGettyImage(): List<GettyImage>?
}