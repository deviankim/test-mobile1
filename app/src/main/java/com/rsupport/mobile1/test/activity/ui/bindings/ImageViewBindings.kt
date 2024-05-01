package com.rsupport.mobile1.test.activity.ui.bindings

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.ImageLoader
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.rsupport.mobile1.test.R
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    val entryPoint = EntryPointAccessors.fromApplication(
        view.context.applicationContext,
        ImageLoaderEntryPoint::class.java
    )
    val coilImageLoader = entryPoint.imageLoader()

    if (url != null) {
        val request = ImageRequest.Builder(view.context)
            .data(url)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .diskCachePolicy(CachePolicy.ENABLED)
            .crossfade(true)
            .placeholder(R.drawable.shape_gray_rectangle)
            .error(R.drawable.image_picture_loading_fail)
            .target(view)
            .build()
        coilImageLoader.enqueue(request)
    } else {
        view.setImageResource(R.drawable.image_picture_loading_fail)
    }
}

@EntryPoint
@InstallIn(SingletonComponent::class)
interface ImageLoaderEntryPoint {
    fun imageLoader(): ImageLoader
}