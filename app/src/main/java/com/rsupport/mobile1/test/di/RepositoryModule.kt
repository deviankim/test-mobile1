package com.rsupport.mobile1.test.di

import com.rsupport.mobile1.test.data.PhotoRepository
import com.rsupport.mobile1.test.data.PhotoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindPhotoRepository(repository: PhotoRepositoryImpl): PhotoRepository
}