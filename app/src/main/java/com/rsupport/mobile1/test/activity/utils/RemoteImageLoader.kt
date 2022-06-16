package com.rsupport.mobile1.test.activity.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import com.rsupport.mobile1.test.R
import kotlinx.coroutines.*
import java.net.URL

object RemoteImageLoader {

    private val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    // Cache를 하지 않을경우 Recycler View Scroll 시 매번 Bitmap을 생성해야 함.
    // 현재는 하드코딩 된 검색어의 결과만 저장하지만, 검색 기능 제공시에는 Clear 기능 필요.
    private val drawableCache = mutableMapOf<String, Bitmap>()

    private val String.isCached get() = drawableCache.containsKey(this)

    fun ImageView.loadRemoteImage(imageUrl: String) {

        fun downloadBitmap(): Bitmap {
            return URL(imageUrl).openConnection().getInputStream()
                .let { BitmapFactory.decodeStream(it) }
        }

        if (imageUrl.isCached) {
            setImageBitmap(drawableCache[imageUrl])
            return
        }

        scope.launch {
            kotlin.runCatching {
                withContext(Dispatchers.IO) { downloadBitmap() }
            }.onSuccess { bitmap ->
                bitmap.cache(imageUrl)
                withContext(Dispatchers.Main) { setImageBitmap(bitmap) }
            }.onFailure {
                it.printStackTrace()
                withContext(Dispatchers.Main) { setFailImage() }
            }
        }
    }

    private fun ImageView.setFailImage() {
        setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_image_not_supported_24))
    }

    private fun Bitmap.cache(key: String) {
        drawableCache[key] = this
    }
}