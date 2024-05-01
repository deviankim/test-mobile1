package com.rsupport.mobile1.test.activity.ui.bindings

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.rsupport.mobile1.test.R

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    if (url != null) {
        view.load(url) {
            crossfade(true)
            placeholder(R.drawable.shape_gray_rectangle)
            error(R.drawable.image_picture_loading_fail)
        }
    } else {
        view.setImageResource(R.drawable.image_picture_loading_fail)
    }
}