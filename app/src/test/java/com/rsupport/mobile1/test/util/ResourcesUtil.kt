package com.rsupport.mobile1.test.util

import java.io.ByteArrayOutputStream

fun getJsonString(obj: Any, path: String): String {
    return obj.javaClass.classLoader?.getResourceAsStream(path)?.let {
        val os = ByteArrayOutputStream()
        val buffer = ByteArray(1024)
        var length = it.read(buffer)
        while (length != -1) {
            os.write(buffer, 0 , length)
            length = it.read(buffer)
        }
        os.toString(Charsets.UTF_8.name())
    } ?: ""
}