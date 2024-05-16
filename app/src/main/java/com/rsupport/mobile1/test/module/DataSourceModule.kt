package com.rsupport.mobile1.test.module

import com.rsupport.mobile1.test.remote.datasource.DataSource
import com.rsupport.mobile1.test.remote.datasource.DataSourceImpl
import com.rsupport.mobile1.test.service.GettyService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(gettyService: GettyService) : DataSource {
        return DataSourceImpl(gettyService)
    }

}