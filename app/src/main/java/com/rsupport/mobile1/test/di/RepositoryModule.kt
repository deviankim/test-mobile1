package com.rsupport.mobile1.test.di

import com.rsupport.mobile1.test.data.repository.DataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideRepository(): DataRepository {
        return DataRepository()
    }
}