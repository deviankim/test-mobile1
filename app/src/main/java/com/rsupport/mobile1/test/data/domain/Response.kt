package com.rsupport.mobile1.test.data.domain

data class Response<T>(
    val data: T,
    val isMore: Boolean = true
)
