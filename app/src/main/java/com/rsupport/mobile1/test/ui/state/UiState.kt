package com.rsupport.mobile1.test.ui.state

sealed class UiState<out T> {
    data object Uninitialized: UiState<Nothing>()
    data object Loading: UiState<Nothing>()
    data object Empty: UiState<Nothing>()
    data class Success<T>(val data: T, var isRefresh: Boolean = false, var isMore: Boolean = false): UiState<T>()
    data class Error(val code: Int? = null, val exception: Throwable? = null): UiState<Nothing>()
}