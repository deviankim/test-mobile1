package com.rsupport.mobile1.test.util

import android.util.Log

object LogUtil {

    private const val LOG_TAG = "Mobile1TestApp"
    private const val EXT_KOTLIN = ".kt"
    private const val BLANK = ""
    private const val OPEN = "["
    private const val CLOSE = "]"
    private const val SEPARATOR_1 = "::"
    private const val SEPARATOR_2 = "()"
    private const val SEPARATOR_3 = " >> "

    fun d(message: String) {
        Log.d(LOG_TAG, message.toLog())
    }

    private fun String.toLog(): String {
        val ste = Thread.currentThread().stackTrace[4]
        return StringBuilder()
            .append(ste.fileName.replace(EXT_KOTLIN, BLANK))
            .append(SEPARATOR_1)
            .append(ste.methodName)
            .append(SEPARATOR_2)
            .append(OPEN)
            .append(ste.lineNumber)
            .append(CLOSE)
            .append(SEPARATOR_3)
            .append(this)
            .toString()
    }

}