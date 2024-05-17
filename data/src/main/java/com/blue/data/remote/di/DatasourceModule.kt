package com.blue.data.remote.di

import com.blue.data.remote.datasource.PhotoDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatasourceModule {
    @Singleton
    @Provides
    fun providePhotoDatasource(retrofit: Retrofit): PhotoDataSourceImpl =
        PhotoDataSourceImpl(retrofit)


}