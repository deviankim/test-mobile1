package com.kimwijin.presentation.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 코루틴 디스패처를 제공하는 DispatcherProvider 인터페이스와 구현 클래스입니다.
 * 이 클래스는 애플리케이션 내에서 필요한 다양한 타입의 코루틴 디스패처를 제공합니다.
 *
 * @author (김위진)
 * @since (2024-05-20)
 */
interface DispatcherProvider {
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
}

@Singleton
class DispatcherProviderImpl @Inject constructor() : DispatcherProvider {
    override val io: CoroutineDispatcher
        get() = Dispatchers.IO

    override val default: CoroutineDispatcher
        get() = Dispatchers.Default
}