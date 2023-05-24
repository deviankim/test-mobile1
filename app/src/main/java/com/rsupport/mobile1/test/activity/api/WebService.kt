package com.rsupport.mobile1.test.activity.api

import okhttp3.ResponseBody
import retrofit2.http.GET

interface WebService {

    @GET("photos/collaboration")
    suspend fun getPhotoCollaboration(): ResponseBody
}