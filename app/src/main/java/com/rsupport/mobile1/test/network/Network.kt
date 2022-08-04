package com.rsupport.mobile1.test.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Network {
    const val DOMAIN = "https://www.gettyimages.com/photos/collaboration"


    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(DOMAIN)
            .build()
    }


    fun provideApiservice(retrofit: Retrofit): NetworkApi {
        return retrofit.create(NetworkApi::class.java)
    }
}