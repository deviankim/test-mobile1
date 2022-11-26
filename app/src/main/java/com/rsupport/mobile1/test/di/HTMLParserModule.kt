package com.rsupport.mobile1.test.di

import com.rsupport.mobile1.test.data.datasource.remote.GettyImageHTMLParser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object HTMLParserModule {
    @Provides
    @Singleton
    fun provideGettyImageHTMLParser() = GettyImageHTMLParser()
}
