package com.rsupport.mobile1.test.di

import com.rsupport.mobile1.test.data.remote.PhotoRemoteDataSource
import com.rsupport.mobile1.test.data.remote.PhotoRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindPhotoRemoteDataSource(dataSource: PhotoRemoteDataSourceImpl): PhotoRemoteDataSource
}