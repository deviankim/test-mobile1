package com.example.crawlin_test.data.response

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jsoup.HttpStatusException

sealed class NetworkResult<out T> {

    data class Success<out T>(val value: T): NetworkResult<T>()
    object Empty: NetworkResult<Nothing>()
    data class Error(val code: Int? = null, val exception: Throwable? = null): NetworkResult<Nothing>()

}

fun <T> safeFlow(apiFunc: suspend () -> T): Flow<NetworkResult<T>> = flow {
    try {
        emit(NetworkResult.Success(apiFunc.invoke()))
    } catch (e: NullPointerException) {
        emit(NetworkResult.Empty)
    } catch (e: HttpStatusException) {
        emit(NetworkResult.Error(code = e.statusCode, exception = e))
    } catch (e: Exception) {
        emit(NetworkResult.Error(exception = e))
    }
}
