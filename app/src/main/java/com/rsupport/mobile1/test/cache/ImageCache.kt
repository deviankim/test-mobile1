package com.rsupport.mobile1.test.cache

import android.graphics.Bitmap

interface ImageCache {
    fun add(url: String, bitmap: Bitmap)
    fun get(url: String): Bitmap?
    fun clear()
}