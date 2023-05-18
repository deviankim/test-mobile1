package com.example.crawlin_test.di


import com.example.crawlin_test.repository.ImageRepository
import com.example.crawlin_test.repository.ImageRepositorySource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class ImageMoudule {

    @Binds
    @Singleton
    abstract fun provideImageRepository(imageRepositorySource: ImageRepositorySource) : ImageRepository

}