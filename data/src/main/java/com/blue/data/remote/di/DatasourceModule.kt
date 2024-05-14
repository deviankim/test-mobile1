package com.blue.data.remote.di

import com.blue.data.remote.datasource.PhotoDataSource
import com.blue.data.remote.datasource.PhotoDataSourceImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatasourceModule {
    @Singleton
    @Provides
    fun providePhotoDatasource(retrofit: Retrofit): PhotoDataSource =
        PhotoDataSourceImpl(retrofit)


}