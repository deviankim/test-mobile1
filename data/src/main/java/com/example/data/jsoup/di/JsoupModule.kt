package com.example.data.jsoup.di

import com.example.data.jsoup.GettyImageJsoupParser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object JsoupModule {

    @Provides
    @Singleton
    fun provideGettyImageJsoupParser(): GettyImageJsoupParser = GettyImageJsoupParser()
}
