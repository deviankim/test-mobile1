package com.blue.data.remote.state

import com.blue.domain.util.Failure

sealed class ResponseState<T>{
    data class Success<T>(val data: T): ResponseState<T>()
    data class Error<T>(val failure: Failure): ResponseState<T>()
}
