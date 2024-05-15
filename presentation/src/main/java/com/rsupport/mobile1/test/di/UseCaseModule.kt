package com.rsupport.mobile1.test.di

import com.blue.domain.repo.FavoriteRepo
import com.blue.domain.repo.PhotoRepo
import com.blue.domain.usecase.favorite.AddFavoriteUseCase
import com.blue.domain.usecase.favorite.DeleteFavoriteUseCase
import com.blue.domain.usecase.favorite.GetFavoriteUseCase
import com.blue.domain.usecase.photo.GetPhotoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideGetPhotoUseCase(repo: PhotoRepo): GetPhotoUseCase =
        GetPhotoUseCase(repo)

    @Provides
    @Singleton
    fun provideGetFavoriteUseCase(repo: FavoriteRepo): GetFavoriteUseCase =
        GetFavoriteUseCase(repo)

    @Provides
    @Singleton
    fun provideAddFavoriteUseCase(repo: FavoriteRepo): AddFavoriteUseCase =
        AddFavoriteUseCase(repo)

    @Provides
    @Singleton
    fun provideDeleteFavoriteUseCase(repo: FavoriteRepo): DeleteFavoriteUseCase =
        DeleteFavoriteUseCase(repo)
}