package com.rsupport.mobile1.test.activity.util

import android.os.Build
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.rsupport.mobile1.test.activity.data.vo.ImageData
import com.rsupport.mobile1.test.activity.ui.adapter.ImageAdapter
import com.rsupport.mobile1.test.activity.viewmodel.MainViewModel

object BindingUtils {
    @JvmStatic
    @BindingAdapter("onClick")
    fun onClick(view: View, listener: View.OnClickListener) {
        view.setOnClickListener(listener)
    }

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun setImageUrl(view: ImageView, url: String?) {
        Log.e("TEST", "imageUrl")
        Glide.with(view.context)
            .load(url)
            .downsample(DownsampleStrategy.AT_MOST)
            .skipMemoryCache(false)
            .override(SIZE_ORIGINAL, SIZE_ORIGINAL)
            .fitCenter()
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(view)
    }

    @JvmStatic
    @BindingAdapter("imageData")
    fun setImageData(view: RecyclerView,
                     items: List<ImageData>?) {
        items?.let {
            view.apply {
                adapter = ImageAdapter().apply {
                    setHasStableIds(true)
                    submitList(it)
                }
            }
        }
    }
}