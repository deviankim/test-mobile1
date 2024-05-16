package com.rsupport.mobile1.test.common

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.collection.LruCache
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

object LruLoader {
    private val cacheSize = (Runtime.getRuntime().maxMemory() / 1024).toInt() / 8

    private val bitmapCache = object : LruCache<String?, Bitmap?>(cacheSize) {
        override fun sizeOf(key: String, value: Bitmap): Int {
            return value.byteCount / 1024
        }
    }

    fun loadImage(url: String, completed: (Bitmap?) -> Unit) {
        if (url.isEmpty()) {
            completed(null)
            return
        }

        GlobalScope.launch(Dispatchers.IO) {
            try {
                var bitmap: Bitmap? = bitmapCache.get(url)

                if (bitmap == null) {
                    bitmap = BitmapFactory.decodeStream(URL(url).openStream())
                    bitmapCache.put(url, bitmap)
                }

                withContext(Dispatchers.Main) {
                    completed(bitmap)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    completed(null)
                }
            }
        }
    }
}