package com.rsupport.mobile1.test.cache

import android.graphics.Bitmap
import androidx.collection.LruCache

class ImageMemoryCache: ImageCache {

    // 최대 Memory (kilobytes)
    private val maxMemory = Runtime.getRuntime().maxMemory() / 1024
    // 최대 Memory 의 1/8만큼 캐시 크기 할당
    private val cacheSize = (maxMemory / 8).toInt()

    private var memoryCache: LruCache<String, Bitmap> = object : LruCache<String, Bitmap>(cacheSize) {
        override fun sizeOf(key: String, value: Bitmap): Int {
            return value.byteCount / 1024
        }
    }

    override fun add(url: String, bitmap: Bitmap) {
        if (memoryCache.get(url) == null) {
            memoryCache.put(url, bitmap)
        }
    }

    override fun get(url: String): Bitmap? {
        return memoryCache.get(url)
    }

    override fun clear() {
        memoryCache.evictAll()
    }
}