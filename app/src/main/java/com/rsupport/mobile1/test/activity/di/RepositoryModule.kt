package com.rsupport.mobile1.test.activity.di

import com.rsupport.mobile1.test.activity.data.GettyImageCrawlingService
import com.rsupport.mobile1.test.activity.data.GettyImageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Singleton
    @Provides
    fun provideGettyImageRepository(
        service: GettyImageCrawlingService
    ): GettyImageRepository {
        return GettyImageRepository(service)
    }
}