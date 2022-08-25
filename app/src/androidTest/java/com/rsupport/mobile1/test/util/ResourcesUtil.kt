package com.rsupport.mobile1.test.util

import androidx.test.platform.app.InstrumentationRegistry

private val assets = InstrumentationRegistry.getInstrumentation().context.assets

fun getJsonString(path: String): String {
    return assets.open(path).bufferedReader().readText()
}