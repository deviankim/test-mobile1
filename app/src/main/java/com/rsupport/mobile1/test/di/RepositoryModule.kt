package com.rsupport.mobile1.test.di

import com.rsupport.mobile1.test.data.GettyImageCrawlingService
import com.rsupport.mobile1.test.data.GettyImageRepository
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