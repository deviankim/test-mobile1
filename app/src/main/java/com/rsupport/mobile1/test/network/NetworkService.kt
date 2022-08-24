package com.rsupport.mobile1.test.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.rsupport.mobile1.test.network.baseurl.BaseUrlFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class NetworkService @Inject constructor(
    baseUrlFactory: BaseUrlFactory
) {

    companion object {
        private const val HEADER_NAME_ACCEPT = "accept"
        private const val HEADER_VALUE_JSON = "application/json"
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
            .baseUrl(baseUrlFactory.getGettyImages())
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(GettyImagesService::class.java)
    }

}