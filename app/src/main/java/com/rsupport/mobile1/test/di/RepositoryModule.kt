package com.rsupport.mobile1.test.di

import com.rsupport.mobile1.test.data.remote.GettyApi
import com.rsupport.mobile1.test.data.repository.GettyRepositoryImpl
import com.rsupport.mobile1.test.domain.repository.GettyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideGettyRepository(api: GettyApi): GettyRepository {
        return GettyRepositoryImpl(api)
    }
}