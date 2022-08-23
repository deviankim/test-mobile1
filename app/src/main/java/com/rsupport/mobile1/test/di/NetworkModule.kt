package com.rsupport.mobile1.test.di

import com.rsupport.mobile1.test.network.GettyImagesService
import com.rsupport.mobile1.test.network.NetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideGettyImagesService(): GettyImagesService {
        return NetworkService().gettyImagesService
    }

}