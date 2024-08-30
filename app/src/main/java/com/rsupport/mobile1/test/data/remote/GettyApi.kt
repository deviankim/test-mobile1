package com.rsupport.mobile1.test.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface GettyApi {
    @GET(GET_PHOTO)
    suspend fun getImageUrls(
        @Path(value = "phrase") phrase: String
    ): String

    companion object {
        const val GET_PHOTO = "/photos/{phrase}"
    }
}