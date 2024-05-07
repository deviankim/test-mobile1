package com.rsupport.mobile1.test.domain.model

sealed class HtmlParseResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : HtmlParseResult<T>()
    data class Error(val exception: Exception) : HtmlParseResult<Nothing>()
}