package com.example.image_loader.util

import java.security.MessageDigest

fun String.hashSHA256(): String {
    val bytes = MessageDigest
        .getInstance("SHA-256")
        .digest(this.toByteArray())
    return bytes.toHexString()
}

fun ByteArray.toHexString(): String {
    return joinToString("") { "%02x".format(it) }
}
