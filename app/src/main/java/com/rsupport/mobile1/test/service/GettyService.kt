package com.rsupport.mobile1.test.service

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface GettyService {
    @GET("/photos/collaboration")
    suspend fun getGettyList(): Response<ResponseBody>
}