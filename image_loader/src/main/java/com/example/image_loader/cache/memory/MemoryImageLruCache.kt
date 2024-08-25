package com.example.image_loader.cache.memory

import android.graphics.Bitmap
import androidx.collection.LruCache
import com.example.image_loader.cache.ImageLruCache

class MemoryImageLruCache: ImageLruCache {

    private val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()
    private val cacheSize = maxMemory / 8

    private val memoryCache = object : LruCache<String, Bitmap>(cacheSize) {
        override fun sizeOf(key: String, value: Bitmap): Int {
            return value.byteCount / 1024
        }
    }

    override suspend fun get(key: String): Bitmap? {
        return memoryCache.get(key)
    }

    override suspend fun put(key: String, bitmap: Bitmap) {
        memoryCache.put(key, bitmap)
    }

    override suspend fun remove(key: String) {
        memoryCache.remove(key)
    }

    override suspend fun clear() {
        memoryCache.evictAll()
    }
}
