package com.blue.data.local.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.blue.data.local.AppDataBase
import com.blue.data.local.dao.FavoriteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Singleton
    @Provides
    fun provideFavoriteDao(database: AppDataBase): FavoriteDao =
        database.FavoriteDao()
}