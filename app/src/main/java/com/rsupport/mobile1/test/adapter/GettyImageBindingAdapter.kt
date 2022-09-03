package com.rsupport.mobile1.test.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.rsupport.mobile1.test.data.GettyImage


@BindingAdapter("thumbnailInfo")
fun setThumbnail(imageView: ImageView, gettyImage: GettyImage) {
    Glide.with(imageView).load(gettyImage.url).into(imageView)
}
