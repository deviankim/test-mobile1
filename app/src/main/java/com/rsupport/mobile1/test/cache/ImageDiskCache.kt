package com.rsupport.mobile1.test.cache

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.util.Log
import com.jakewharton.disklrucache.DiskLruCache
import com.jakewharton.disklrucache.DiskLruCache.Snapshot
import com.rsupport.mobile1.test.BuildConfig
import com.rsupport.mobile1.test.extension.hashSHA256
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.File
import java.io.IOException
import java.util.concurrent.locks.Condition
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class ImageDiskCache(@ApplicationContext context: Context) : ImageCache {

    companion object {
        private const val DISK_CACHE_SUBDIR: String = "thumbnails"
        private const val VALUE_COUNT: Int = 1
        private const val DISK_CACHE_SIZE: Long = 1024L * 1024L * 10L // 10MB
        private const val IO_BUFFER_SIZE: Int = 8 * 1024 // 8KB
        private const val COMPRESS_QUALITY: Int = 100
    }

    private val TAG = ImageDiskCache::class.simpleName
    private var diskLruCache: DiskLruCache? = null
    private val diskCacheLock = ReentrantLock()
    private val diskCacheLockCondition: Condition = diskCacheLock.newCondition()
    private var diskCacheStarting = true

    init {
        diskCacheLock.withLock {
            val cacheFile = getDiskCacheDir(context)
            diskLruCache =
                DiskLruCache.open(cacheFile, BuildConfig.VERSION_CODE, VALUE_COUNT, DISK_CACHE_SIZE)
            diskCacheStarting = false
            diskCacheLockCondition.signalAll() // Wake any waiting threads
        }
    }

    override fun add(url: String, bitmap: Bitmap) {
        diskCacheLock.withLock {
                val key = url.hashSHA256()
                var editor: DiskLruCache.Editor? = null
                try {
                    editor = diskLruCache?.edit(key)
                    if (editor == null) {
                        return
                    }
                    if (writeBitmapToFile(bitmap, editor)) {
                        diskLruCache?.flush()
                        editor.commit()
                    } else {
                        editor.abort()
                    }
                } catch (e: IOException) {
                    try {
                        editor?.abort()
                    } catch (ignored: IOException) {}
                }

        }
    }

    override fun get(url: String): Bitmap? {
        diskCacheLock.withLock {
            // Disk 캐시가 생성될 때 까지 기다림
            while (diskCacheStarting) {
                try {
                    diskCacheLockCondition.await()
                } catch (_: InterruptedException) {}
            }

            val key = url.hashSHA256()
            val snapshot: Snapshot? = diskLruCache?.get(key)
            val bitmap = snapshot?.getInputStream(0)?.use { inputStream ->
                val buffIn = BufferedInputStream(inputStream, IO_BUFFER_SIZE)
                BitmapFactory.decodeStream(buffIn)
            }

            return bitmap
        }
    }

    override fun clear() {
        diskCacheLock.withLock {
            try {
                diskLruCache?.delete()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    /**
     * 외부 캐시 저장소 또는 내부 캐시 저장소 위치 반환
     */
    private fun getDiskCacheDir(context: Context): File {
        val cachePath =
            if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()
                || !Environment.isExternalStorageRemovable()
            ) {
                context.externalCacheDir?.path
            } else {
                context.cacheDir.path
            }

        return File(cachePath + File.separator + DISK_CACHE_SUBDIR)
    }

    private fun writeBitmapToFile(bitmap: Bitmap, editor: DiskLruCache.Editor): Boolean {
        return BufferedOutputStream(editor.newOutputStream(0), IO_BUFFER_SIZE).use { outPutStream ->
            bitmap.compress(Bitmap.CompressFormat.JPEG, COMPRESS_QUALITY, outPutStream)
        }
    }
}