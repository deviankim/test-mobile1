package com.blue.domain.state

import com.blue.domain.util.Failure

sealed class ResourceState<T>{
    data class Success<T>(val data: T): ResourceState<T>()
    data class Error<T>(val failure: Failure): ResourceState<T>()
    class Loading<T>: ResourceState<T>()
}
