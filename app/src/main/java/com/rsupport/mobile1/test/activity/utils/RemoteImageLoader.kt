package com.rsupport.mobile1.test.activity.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import com.rsupport.mobile1.test.R
import kotlinx.coroutines.*
import java.net.URL

class RemoteImageLoader {

    private val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    private var preLoadRemoteImageJob: Job? = null

    private val String.isCached get() = drawableCache.containsKey(this)

    fun loadRemoteImage(imageView: ImageView, imageUrl: String) {

        fun downloadBitmap(): Bitmap {
            return URL(imageUrl).openConnection().getInputStream()
                .let { BitmapFactory.decodeStream(it) }
        }

        preLoadRemoteImageJob?.cancel()

        imageView.setBlankImage()

        if (imageUrl.isCached) {
            imageView.setImageBitmap(drawableCache[imageUrl])
            return
        }

        preLoadRemoteImageJob = scope.launch {
            kotlin.runCatching {
                withContext(Dispatchers.IO) { downloadBitmap() }
            }.onSuccess { bitmap ->
                bitmap.cache(imageUrl)
                withContext(Dispatchers.Main) { imageView.setImageBitmap(bitmap) }
            }.onFailure {
                it.printStackTrace()
                withContext(Dispatchers.Main) { imageView.setFailImage() }
            }
        }
    }

    private fun ImageView.setFailImage() {
        setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_image_not_supported_24))
    }

    private fun ImageView.setBlankImage() {
        setImageDrawable(null)
    }

    private fun Bitmap.cache(key: String) {
        drawableCache[key] = this
    }

    companion object {
        private val drawableCache = mutableMapOf<String, Bitmap>()

        fun clearCache() {
            drawableCache.clear()
        }
    }
}