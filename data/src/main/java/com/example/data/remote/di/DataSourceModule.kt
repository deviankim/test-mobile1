package com.example.data.remote.di

import com.example.data.remote.source.GettyImagesRemoteDataSource
import com.example.data.remote.source.GettyImagesRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Singleton
    @Binds
    fun bindGettyImagesRemoteSource(
        gettyImagesRemoteSourceImpl: GettyImagesRemoteDataSourceImpl
    ): GettyImagesRemoteDataSource
}
