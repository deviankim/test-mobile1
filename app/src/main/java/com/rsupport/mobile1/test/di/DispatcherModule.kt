package com.rsupport.mobile1.test.di

import com.kimwijin.presentation.base.DispatcherProvider
import com.kimwijin.presentation.base.DispatcherProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import javax.inject.Singleton

/**
 * Hilt 모듈로 정의된 DispatcherModule 클래스입니다.
 * 이 모듈은 애플리케이션에서 사용될 다양한 CoroutineDispatcher 객체를 제공 하는 역할을 합니다.
 * Hilt의 의존성 주입 기능을 통해 필요한 곳에서 쉽게 Dispatcher 객체를 주입 받아 사용할 수 있도록 합니다.
 *
 * Module 어노테이션은 Hilt가 이 클래스를 모듈로 인식하게 합니다.
 * 각 Provides 함수는 특정 타입의 객체를 생성하고 이를 의존성 그래프에 추가하는 역할을 합니다.
 *
 * 제공되는 디스패처는 Main, IO, Default 가 있으며, 각각의 사용 케이스에 맞게 사용됩니다.
 * 사용 예:
 * @Inject lateinit var dispatcher: CoroutineDispatcher
 * fun exampleFunction() {
 *     CoroutineScope(dispatcher).launch {
 *         // 실행할 코드
 *     }
 * }
 *
 *
 * @author (김위진)
 * @since (2024-05-20)
 */
@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

    @Provides
    fun bindDispatcherProvider(): DispatcherProvider {
        return DispatcherProviderImpl()
    }

    @Provides
    @Singleton
    @Named("Main")
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @Provides
    @Singleton
    @Named("IO")
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Singleton
    @Named("Default")
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
}