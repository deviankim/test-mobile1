package com.rsupport.mobile1.test.di

import com.rsupport.mobile1.test.network.ImageService
import com.rsupport.mobile1.test.repository.ImageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideImageService(
    ) : ImageService = ImageService()

    @Singleton
    @Provides
    fun provideImageRepository(
        imageService: ImageService
    ) : ImageRepository = ImageRepository(imageService = imageService)
}