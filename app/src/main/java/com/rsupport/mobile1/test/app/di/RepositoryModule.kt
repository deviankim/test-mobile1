package com.rsupport.mobile1.test.app.di

import com.rsupport.mobile1.test.data.repository.MainRepositoryImpl
import com.rsupport.mobile1.test.domain.repository.MainRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMainRepository(repository: MainRepositoryImpl): MainRepository
}