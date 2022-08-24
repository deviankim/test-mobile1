package com.rsupport.mobile1.test.di

import com.rsupport.mobile1.test.network.GettyImagesService
import com.rsupport.mobile1.test.network.NetworkService
import com.rsupport.mobile1.test.network.baseurl.BaseUrlFactoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
open class NetworkModule {

    @Provides
    @Singleton
    open fun provideNetworkService(): NetworkService {
        return NetworkService(BaseUrlFactoryImpl())
    }

    @Provides
    @Singleton
    fun provideGettyImagesService(networkService: NetworkService): GettyImagesService {
        return networkService.gettyImagesService
    }

}