package com.rsupport.mobile1.test.util

import java.io.InputStreamReader

fun getJsonString(obj: Any, path: String): String {
    return InputStreamReader(obj.javaClass.classLoader?.getResourceAsStream(path)).readText()
}