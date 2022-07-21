package com.rsupport.mobile1.test.activity.common

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rsupport.mobile1.test.R
import com.rsupport.mobile1.test.activity.presenter.MainActivity

@BindingAdapter(value = ["thumbnailImg"])
fun ImageView.thumbnailImg(url: String?) {
    try {
        context?.let {
            if (!url.isNullOrEmpty()) {
                Glide.with(it)
                    .load(url)
                    .apply(RequestOptions().placeholder(R.drawable.img_empty))
                    .into(this)
            } else {
                // default view
                Glide.with(it)
                    .load(R.drawable.img_empty)
                    .into(this)
            }
        }
    } catch (e: Exception) {
        Log.e(MainActivity.TAG, e.toString())
    }
}
