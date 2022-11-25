package com.rsupport.mobile1.test.di

import com.rsupport.mobile1.test.data.repository.ImageRepositoryImpl
import com.rsupport.mobile1.test.domain.repository.ImageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideImageRepository(imageRepository: ImageRepositoryImpl) : ImageRepository
}