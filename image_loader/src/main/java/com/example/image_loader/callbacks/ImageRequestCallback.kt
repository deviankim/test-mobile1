package com.example.image_loader.callbacks

import android.graphics.Bitmap

interface ImageRequestCallback {

    fun onStart()

    fun onSuccess(bitMap: Bitmap)

    fun onError(error: Throwable) {}
}
