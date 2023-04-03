package com.rsupport.mobile1.test.activity

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.rsupport.mobile1.test.R

object BaseBindingAdapter {

    @JvmStatic
    @BindingAdapter("loadImageFromUrl")
    fun ImageView.loadImageFromUrl(thumbUrl: String) {
        Glide.with(this)
            .load(thumbUrl)
            .centerInside()
            .error(R.drawable.ic_launcher_background)
            .into(this)
    }
}

