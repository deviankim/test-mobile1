package com.rsupport.mobile1.test.extension

import android.content.Context
import com.rsupport.mobile1.test.R
import java.net.UnknownHostException

fun Throwable.parseError(context: Context): String? {
    return when (this) {
        is UnknownHostException -> context.getString(R.string.no_internet_connection)
        is NoSuchElementException -> context.getString(R.string.no_search_results)
        else -> localizedMessage
    }
}
