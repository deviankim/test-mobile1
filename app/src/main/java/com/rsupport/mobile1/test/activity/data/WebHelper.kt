package com.rsupport.mobile1.test.activity.data

interface WebHelper {
    suspend fun requestGettyImage(): List<GettyImage>?
}