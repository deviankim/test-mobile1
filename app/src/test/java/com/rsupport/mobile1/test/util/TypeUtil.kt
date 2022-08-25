package com.rsupport.mobile1.test.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> String.toInstance(): T {
    val type = object : TypeToken<T>(){}.type
    return Gson().fromJson(this, type)
}