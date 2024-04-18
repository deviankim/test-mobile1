package com.rsupport.mobile1.test.bindingAdapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.rsupport.mobile1.test.R

object ViewBindingAdapter {
    @BindingAdapter("loadImageUrl")
    @JvmStatic
    fun loadImageUrl(view: ImageView, url: String?) {
        Glide.with(view.context)
            .load(url)
            .error(R.drawable.ic_launcher_foreground)
            .into(view)
    }
}