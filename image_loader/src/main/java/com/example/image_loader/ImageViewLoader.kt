package com.example.image_loader

import android.widget.ImageView
import com.example.image_loader.request.ImageRequest

fun ImageView.load(
    data: String,
    builder: ImageRequest.Builder.() -> Unit = {},
) {
    val request = ImageRequest.Builder()
        .data(data)
        .target(this)
        .apply(builder)
        .build()

    val imageLoader = ImageLoader.getInstance(context)
    imageLoader.into(request)
}
