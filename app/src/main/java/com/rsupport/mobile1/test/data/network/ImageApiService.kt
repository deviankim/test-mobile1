package com.rsupport.mobile1.test.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Lee on 2024-08-23.
 * This singleton object provides a Retrofit instance for making network requests.
 * It uses OkHttpClient and GsonConverterFactory to create the instance.
 */
object ImageApiService {
    // Base URL for the API
    private const val BASE_URL = "https://www.gettyimages.com"

    // Lazy initialization of the ImageApi instance
    val instance: ImageApi by lazy {
        // Build the Retrofit instance
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // Converts JSON to Kotlin objects
            .client(OkHttpClient.Builder().build()) // Adds OkHttpClient to handle network operations
            .build()

        // Create and return the ImageApi implementation
        retrofit.create(ImageApi::class.java)
    }
}