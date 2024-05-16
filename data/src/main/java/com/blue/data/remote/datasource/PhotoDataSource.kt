package com.blue.data.remote.datasource

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Streaming

interface PhotoDataSource {
    @Streaming
    @GET("photos/collaboration")
    suspend fun getPhotoDataSource(): Response<String>
}