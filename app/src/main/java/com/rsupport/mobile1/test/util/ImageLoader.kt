package com.example.crawlin_test.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.LruCache
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL


// image memory cache(LRU)
object ImageLoader {

    private val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()
    val cacheSize = maxMemory / 8  // 메모리 사이즈 중 1/8 사용

    // value(이미지 bitmap) 크기를 넘겨준다. 이 크기들의 합으로 maxsize와 비교하여 cache를 유지
    private val bitmapCache = object : LruCache<String?, Bitmap?>(cacheSize) {
        override fun sizeOf(key: String?, value: Bitmap?): Int {
            return value?.byteCount!! / 1024
        }
    }

    fun loadImage(url: String, completed: (Bitmap?) -> Unit)  {

        // 이미지 url값이 없을경우
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