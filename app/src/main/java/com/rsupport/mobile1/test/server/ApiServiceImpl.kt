package com.rsupport.mobile1.test.server

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal class ApiServiceImpl {
    companion object {
        fun getService(): ApiService {
            return Retrofit.Builder()
                .baseUrl(ApiConst.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}