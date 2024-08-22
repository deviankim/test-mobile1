package com.rsupport.mobile1.test.extension

import okhttp3.internal.and
import java.security.MessageDigest

/**
 * SHA256
 */
fun String.hashSHA256(): String {
    val md = MessageDigest.getInstance("SHA-256")
    md.update(this.toByteArray())
    return md.digest().byteToHexString()
}

/**
 * Hex 문자열 변환
 */
fun ByteArray.byteToHexString(): String {
    val sb = StringBuilder()
    for (b in this) {
        sb.append(((b and 0xff) + 0x100).toString(16).substring(1))
    }
    return sb.toString()
}