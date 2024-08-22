package com.example.image_loader.cache

import android.graphics.Bitmap

interface ImageLruCache {

    suspend fun get(key: String): Bitmap?
    suspend fun put(key: String, bitmap: Bitmap)
    suspend fun remove(key: String)
    suspend fun clear()
}
