package com.example.image_loader.request

import android.graphics.Bitmap
import android.widget.ImageView
import com.example.image_loader.callbacks.ImageRequestCallback

class ImageRequest(
    val data: String,
    val target: ImageView,
    val listener: ImageRequestCallback? = null,
) {
    class Builder {
        private lateinit var data: String
        private lateinit var target: ImageView
        private var listener: ImageRequestCallback? = null

        fun data(data: String) = apply { this.data = data }
        fun target(target: ImageView) = apply { this.target = target }
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
                data = data,
                target = target,
                listener = listener
            )
        }
    }
}
