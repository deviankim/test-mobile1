package com.rsupport.mobile1.test.di

import com.rsupport.mobile1.test.core.repository.ImageRepository
import com.rsupport.mobile1.test.core.repository.ImageRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {
    @Binds
    abstract fun bindsImageRepository(imageRepositoryImpl: ImageRepositoryImpl): ImageRepository

}