package com.rsupport.mobile1.test.network.di

import com.rsupport.mobile1.test.network.datasource.ImageDataSourceImpl
import com.rsupport.mobile1.test.network_api.datasource.ImageDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataSourceModule {
    @Binds
    abstract fun bindsImageDataSource(imageDataSourceImpl: ImageDataSourceImpl): ImageDataSource
}