package com.rsupport.mobile1.test.di

import com.kimwijin.data.source.remote.JsoupWebServiceImpl
import com.kimwijin.data.source.remote.WebService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt 모듈로 정의된 NetworkModule 클래스 입니다.
 * 이 모듈은 애플리케이션에서 사용될 Network Service 등을 제공합니다.
 *
 * @author (김위진)
 * @since (2024-05-21)
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideWebService(): WebService = JsoupWebServiceImpl()

}