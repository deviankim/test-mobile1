package com.rsupport.mobile1.test.activity.presentation.module

import com.rsupport.mobile1.test.activity.data.repository.remote.GettyRemoteDataSourceImpl
import com.rsupport.mobile1.test.activity.data.repository.remote.GettyRepositoryImpl
import com.rsupport.mobile1.test.activity.domain.repository.GettyRepository
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
    fun getGettyItem(
        gettyRemoteDataSource: GettyRemoteDataSourceImpl
    ) : GettyRepository {
        return GettyRepositoryImpl(
            gettyRemoteDataSource
        )
    }
}