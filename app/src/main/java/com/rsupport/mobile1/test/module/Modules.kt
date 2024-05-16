package com.rsupport.mobile1.test.module

import com.rsupport.mobile1.test.MobileApplication
import com.rsupport.mobile1.test.service.GettyService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {
    single {
        Retrofit.Builder()
            .baseUrl((androidApplication() as MobileApplication).serviceUrl)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(GettyService::class.java)
    }

    single {
        OkHttpClient.Builder().addInterceptor(get() as HttpLoggingInterceptor)
            .build()
    }

    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.NONE
        }
    }
}