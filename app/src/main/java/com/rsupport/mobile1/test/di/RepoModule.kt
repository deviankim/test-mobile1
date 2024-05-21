package com.rsupport.mobile1.test.di

import com.kimwijin.data.repository.RepositoryImpl
import com.kimwijin.data.source.remote.WebService
import com.kimwijin.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Named
import javax.inject.Singleton

/**
 * Hilt 모듈로 정의된 RepoModule 클래스 입니다.
 * 이 모듈은 애플리케이션의 다양한 데이터 저장소 인스턴스를 제공 합니다.
 * Repository 객체 들은 데이터 관련 로직을 캡슐화 하여 관련된 데이터를 처리 합니다.
 *
 * @author (김위진)
 * @since (2024-05-21)
 */
@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    @Singleton
    fun provideRepository(
        webService: WebService
    ): Repository = RepositoryImpl(webService)

}