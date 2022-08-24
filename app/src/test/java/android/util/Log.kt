@file:JvmName("Log")

package android.util

fun d(tag: String, msg: String): Int {
    println("DEBUG: $tag: $msg")
    return 0
}