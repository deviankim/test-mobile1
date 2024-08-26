package com.example.data.di

import com.example.data.repository.GettyImageRepositoryImpl
import com.example.domain.repository.GettyImageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Singleton
    @Binds
    fun bindGettyImageRepository(
        gettyImageRepositoryImpl: GettyImageRepositoryImpl
    ): GettyImageRepository
}
