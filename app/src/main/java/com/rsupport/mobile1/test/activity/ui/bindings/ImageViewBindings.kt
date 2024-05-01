package com.rsupport.mobile1.test.activity.ui.bindings

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.rsupport.mobile1.test.R
import com.rsupport.mobile1.test.activity.App.Companion.imageLoader

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
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
        imageLoader.enqueue(request)
    } else {
        view.setImageResource(R.drawable.image_picture_loading_fail)
    }
}