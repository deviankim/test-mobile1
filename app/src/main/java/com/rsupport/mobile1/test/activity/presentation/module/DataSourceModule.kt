package com.rsupport.mobile1.test.activity.presentation.module

import com.rsupport.mobile1.test.activity.data.api.GettyService
import com.rsupport.mobile1.test.activity.data.repository.remote.GettyRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Singleton
    @Provides
    fun getGettyItem(gettyService: GettyService) = GettyRemoteDataSourceImpl(gettyService)
}