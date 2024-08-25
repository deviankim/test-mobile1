package com.example.image_loader.cache.disk

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import com.example.image_loader.cache.ImageLruCache
import com.jakewharton.disklrucache.DiskLruCache
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.io.File
import java.io.IOException

class DiskImageLruCache(private val context: Context) : ImageLruCache {

    private val mutex = Mutex()
    private var diskLruCache: DiskLruCache? = null

    init {
        initializeDiskCache()
    }

    override suspend fun get(key: String): Bitmap? {
        return mutex.withLock {
            diskLruCache?.get(key)?.use { snapshot ->
                snapshot.getInputStream(VALUE_INDEX)?.use {
                    BitmapFactory.decodeStream(it)
                }
            }
        }
    }

    override suspend fun put(key: String, bitmap: Bitmap) {
        mutex.withLock {
            diskLruCache?.edit(key)?.also { editor ->
                try {
                    editor.newOutputStream(VALUE_INDEX).use { outputStream ->
                        bitmap.compress(Bitmap.CompressFormat.JPEG, QUALITY, outputStream)
                        editor.commit()
                    }
                } catch (e: IOException) {
                    editor.abort()
                }
            }
        }
    }

    override suspend fun remove(key: String) {
        mutex.withLock {
            diskLruCache?.remove(key)
        }
    }

    override suspend fun clear() {
        mutex.withLock {
            diskLruCache?.delete()
            initializeDiskCache()
        }
    }

    private fun initializeDiskCache() {
        val cacheDir = getDiskCacheDir(context, DISK_CACHE_SUBDIR)
        try {
            diskLruCache = DiskLruCache.open(cacheDir, 1, 1, DISK_CACHE_SIZE)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun getDiskCacheDir(context: Context, uniqueName: String): File {
        val cachePath = if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState() ||
            !isExternalStorageRemovable()
        ) {
            context.externalCacheDir?.path
        } else {
            context.cacheDir.path
        }
        return File(cachePath + File.separator + uniqueName)
    }

    private fun isExternalStorageRemovable(): Boolean {
        return Environment.isExternalStorageRemovable()
    }

    companion object {
        private const val DISK_CACHE_SIZE = 1024 * 1024 * 10L // 10MB
        private const val DISK_CACHE_SUBDIR = "thumbnails"
        private const val VALUE_INDEX = 0
        private const val QUALITY = 100
    }
}