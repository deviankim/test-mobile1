package com.rsupport.mobile1.test.activity.api

import okhttp3.ResponseBody

sealed class Resource<out T> {
    data class Success<out T>(val value: T) : Resource<T>()
    data class Failure(
        val errorCode: Int?,
        val errorBody: ResponseBody?
    ) : Resource<Nothing>()
}
