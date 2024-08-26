package com.example.image_loader.callbacks

import com.example.image_loader.request.ImageRequest

fun interface RestartCallback {
    fun restart(request: ImageRequest)
}