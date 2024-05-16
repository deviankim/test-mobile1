package com.rsupport.mobile1.test.module

import com.rsupport.mobile1.test.remote.Repository
import com.rsupport.mobile1.test.remote.RepositoryImpl
import com.rsupport.mobile1.test.remote.datasource.DataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class  RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(remoteDataSource: DataSourceImpl): Repository {
        return RepositoryImpl(remoteDataSource)
    }
}