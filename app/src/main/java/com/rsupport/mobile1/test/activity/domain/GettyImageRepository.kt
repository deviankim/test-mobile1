package com.rsupport.mobile1.test.activity.domain

interface GettyImageRepository {
    suspend fun getImage(): List<GettyImageResponse>?
}