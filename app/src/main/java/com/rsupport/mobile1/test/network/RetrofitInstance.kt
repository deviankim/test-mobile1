package com.rsupport.mobile1.test.network

import com.rsupport.mobile1.test.util.Constants
import com.rsupport.mobile1.test.util.SkipNetworkInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {
        private val gettyImageNetworkService by lazy {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(SkipNetworkInterceptor())
                .build()
            Retrofit.Builder()
                .baseUrl(Constants.GETTY_IMAGE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        }
    }
    val gettyImageApi by lazy {
        gettyImageNetworkService.create(API::class.java)
    }

}