package com.rsupport.mobile1.test.cache

import android.content.Context
import android.graphics.Bitmap
import dagger.hilt.android.qualifiers.ApplicationContext

class ImageCacheHelper(@ApplicationContext context: Context) {

    private val memoryCache: ImageCache = ImageMemoryCache()
    private val diskCache: ImageCache = ImageDiskCache(context)

    fun writeToCache(url: String, bitmap: Bitmap) {
        memoryCache.add(url, bitmap)
        diskCache.add(url, bitmap)
    }

    fun readFromCache(url: String): Bitmap? {
        return memoryCache.get(url) ?: diskCache.get(url)?.also {
            memoryCache.add(url, it)
        }
    }

    fun clearCache() {
        memoryCache.clear()
        diskCache.clear()
    }
}