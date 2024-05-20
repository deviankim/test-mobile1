package com.kimwijin.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * 모든 ViewModel의 기본 클래스로 사용되는 open 클래스 입니다.
 * 이 클래스는 Android의 ViewModel 기능을 상속받아 ViewModel의 기본적인 생명주기를 관리하며,
 * DispatcherProvider 인터페이스를 통해 코루틴 실행을 위한 디스패처를 제공합니다.
 *
 * BaseViewModel은 파생되는 모든 ViewModel 클래스에 공통 로직을 제공 하기 위해 사용됩니다.
 * 이를 통해 코드 중복을 줄이고, 디스패처의 일관된 관리를 가능하게 합니다.
 *
 * @author (김위진)
 * @since (2024-05-20)
 */
open class BaseViewModel(dispatcherProvider: DispatcherProvider) : ViewModel(),
    DispatcherProvider by dispatcherProvider

inline fun BaseViewModel.onMain(
    crossinline body: suspend CoroutineScope.() -> Unit
) = viewModelScope.launch {
    body(this)
}

inline fun BaseViewModel.onIO(
    crossinline body: suspend CoroutineScope.() -> Unit
) = viewModelScope.launch(io) {
    body(this)
}

inline fun BaseViewModel.onDefault(
    crossinline body: suspend CoroutineScope.() -> Unit
) = viewModelScope.launch(default) {
    body(this)
}