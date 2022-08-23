package com.rsupport.mobile1.test.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkService {

    companion object {
        private const val HEADER_NAME_ACCEPT = "accept"
        private const val HEADER_VALUE_JSON = "application/json"

        private const val BASE_URL_GETTY_IMAGES = "https://www.gettyimages.com/"
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(Interceptor { chain ->
            chain.request().newBuilder()
                .addHeader(HEADER_NAME_ACCEPT, HEADER_VALUE_JSON)
                .build()
                .let { chain.proceed(it) }
        })
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    val gettyImagesService: GettyImagesService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_GETTY_IMAGES)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(GettyImagesService::class.java)
    }

}