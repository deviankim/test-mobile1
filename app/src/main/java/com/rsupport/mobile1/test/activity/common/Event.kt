package com.rsupport.mobile1.test.activity.common

open class Event<out T>(private val content: T) {
    var hasBeenHandled = false
        private set

    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true // 이벤트 처리 표시 후
            content // 값을 반환함
        }
    }

    fun peekContent(): T = content
}