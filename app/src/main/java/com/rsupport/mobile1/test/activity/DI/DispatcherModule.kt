package com.rsupport.mobile1.test.activity.DI

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

    @Provides
    @Singleton
    fun provideIODispatcher() = Dispatchers.IO

    @Provides
    @Singleton
    fun provideMainDispatcher() = Dispatchers.Main
}