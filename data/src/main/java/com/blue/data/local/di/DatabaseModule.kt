package com.blue.data.local.di

import android.content.Context
import androidx.room.Room
import com.blue.data.local.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDataBase =
        Room.databaseBuilder(appContext, AppDataBase::class.java, "myDataBase")
            .build()
}