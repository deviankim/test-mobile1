package com.blue.data.repoImpl.di

import com.blue.data.local.dao.FavoriteDao
import com.blue.data.remote.datasource.PhotoDataSourceImpl
import com.blue.data.repoImpl.FavoriteRepoImpl
import com.blue.data.repoImpl.PhotoRepoImpl
import com.blue.domain.repo.FavoriteRepo
import com.blue.domain.repo.PhotoRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {
    @Singleton
    @Provides
    fun providePhotoRepo(dataSource: PhotoDataSourceImpl): PhotoRepo = PhotoRepoImpl(dataSource)

    @Singleton
    @Provides
    fun provideFavoriteRepo(dao: FavoriteDao): FavoriteRepo =
        FavoriteRepoImpl(dao)
}