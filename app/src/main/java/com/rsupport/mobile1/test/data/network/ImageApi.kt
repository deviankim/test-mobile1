package com.rsupport.mobile1.test.data.network

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Lee on 2024-08-23.
 * This interface defines the Retrofit API for fetching images from the server.
 * The API is expected to return the HTML content containing the images.
 */
interface ImageApi {
    /**
     * Makes a GET request to the server to fetch images.
     * The images are retrieved as part of the HTML response body.
     *
     * @return A Response object containing the raw HTML response as ResponseBody.
     */
    @GET("/photos/collaboration")
    suspend fun fetchImages(): Response<ResponseBody>
}