package com.rsupport.mobile1.test.activity.DI

import com.rsupport.mobile1.test.activity.Data.LocalDB.ImageDao
import com.rsupport.mobile1.test.activity.Data.Repository.ImageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(
        imageDao: ImageDao,
        IODispatcher: CoroutineDispatcher,
    ) = ImageRepository(imageDao = imageDao ,IODispatcher = IODispatcher)
}