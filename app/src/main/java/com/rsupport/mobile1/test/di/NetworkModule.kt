package com.rsupport.mobile1.test.di

import android.util.Log
import com.rsupport.mobile1.test.common.Constants
import com.rsupport.mobile1.test.data.remote.GettyApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val OKHTTP_LOG_TAG = "OKHTTP_BODY"

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addNetworkInterceptor(HttpLoggingInterceptor { message ->
                Log.d(OKHTTP_LOG_TAG, message)
            }.apply { level = HttpLoggingInterceptor.Level.BODY })
        }.build()
    }

    @Provides
    @Singleton
    fun provideGettyApi(okHttpClient: OkHttpClient): GettyApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(GettyApi::class.java)
    }
}