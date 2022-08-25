package com.rsupport.mobile1.test.activity.DI

import android.content.Context
import androidx.room.Room
import com.rsupport.mobile1.test.activity.Data.LocalDB.ImageDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDBModule {

    @Provides
    @Singleton
    fun provideDB(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, ImageDatabase::class.java, ImageDatabase.DB_NAME).build()

    @Provides
    @Singleton
    fun provideDao(imageDatabase : ImageDatabase) = imageDatabase.Dao()
}