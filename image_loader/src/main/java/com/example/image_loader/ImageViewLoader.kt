package com.example.image_loader

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.widget.ImageView
import com.example.image_loader.request.ImageRequest

fun ImageView.load(
    data: String,
    builder: ImageRequest.Builder.() -> Unit = {},
) {
    val request = ImageRequest.Builder()
        .setUrl(data)
        .setTarget(this)
        .apply(builder)
        .build()

    val imageLoader = ImageLoader.getInstance(context)
    imageLoader.enqueue(request)
}

fun ImageView.into(
    bitmap: Bitmap
) {
    val drawable = BitmapDrawable(resources, bitmap)
    setImageDrawable(drawable)
}
