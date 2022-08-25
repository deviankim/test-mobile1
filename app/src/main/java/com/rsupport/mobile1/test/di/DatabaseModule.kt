package com.rsupport.mobile1.test.di

import android.content.Context
import androidx.room.Room
import com.rsupport.mobile1.test.data.database.GettyDatabase
import com.rsupport.mobile1.test.util.Constants.GETTY_DATABASE_NAME
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
    fun providerDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        GettyDatabase::class.java,
        GETTY_DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun providerGettyDao(database: GettyDatabase) = database.gettyDao()
}