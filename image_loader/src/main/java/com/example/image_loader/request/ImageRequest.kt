package com.example.image_loader.request

import android.graphics.Bitmap
import android.widget.ImageView
import com.example.image_loader.callbacks.ImageRequestCallback

class ImageRequest(
    val url: String,
    val target: ImageView,
    val listener: ImageRequestCallback? = null,
) {
    class Builder {
        private lateinit var url: String
        private lateinit var target: ImageView
        private var listener: ImageRequestCallback? = null

        fun setUrl(url: String) = apply { this.url = url }
        fun setTarget(target: ImageView) = apply { this.target = target }
        fun listener(listener: ImageRequestCallback?) = apply { this.listener = listener }

        inline fun listener(
            crossinline onStart: () -> Unit = {},
            crossinline onError: (error: Throwable) -> Unit = { _ -> },
            crossinline onSuccess: (bitMap: Bitmap) -> Unit = { _ -> }
        ) = listener(object : ImageRequestCallback {
            override fun onStart() = onStart()
            override fun onSuccess(bitMap: Bitmap) = onSuccess(bitMap)
            override fun onError(error: Throwable) = onError(error)
        })

        fun build(): ImageRequest {
            return ImageRequest(
                url = url,
                target = target,
                listener = listener
            )
        }
    }
}
