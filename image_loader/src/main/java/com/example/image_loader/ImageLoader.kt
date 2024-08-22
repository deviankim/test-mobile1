package com.example.image_loader

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import com.example.image_loader.cache.memory.MemoryImageLruCache
import com.example.image_loader.request.ImageRequest
import com.example.image_loader.util.hashSHA256
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.URL

class ImageLoader private constructor(
    private val memoryLruCache: MemoryImageLruCache
) {

    fun into(request: ImageRequest) {
        CoroutineScope(Dispatchers.Main).launch {
            request.listener?.onStart()
            val bitmap = withContext(Dispatchers.IO) {
                loadImage(request)
            }
            bitmap?.let {
                val drawable = BitmapDrawable(request.target.resources, it)
                request.target.setImageDrawable(drawable)
                request.listener?.onSuccess(it)
            }
        }
    }

    private suspend fun loadImage(request: ImageRequest): Bitmap? {
        val imageUrl = request.data
        val key = imageUrl.hashSHA256()
        return memoryLruCache.get(key) ?: try {
            downloadImage(key).also { bitmap ->
                memoryLruCache.put(key, bitmap)
            }
        } catch (e: IOException) {
            request.listener?.onError(e)
            null
        }
    }

    private suspend fun downloadImage(imageUrl: String): Bitmap = withContext(Dispatchers.IO) {
        val url = URL(imageUrl)
        val connection = url.openConnection()
        connection.doInput = true
        connection.connect()
        BitmapFactory.decodeStream(connection.inputStream)
    }

    companion object {
        @Volatile
        private var instance: ImageLoader? = null

        fun getInstance(): ImageLoader {
            return instance ?: synchronized(this) {
                instance ?: ImageLoader(MemoryImageLruCache())
                    .also { instance = it }
            }
        }
    }
}
