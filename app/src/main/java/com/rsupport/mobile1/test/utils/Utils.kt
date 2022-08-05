package com.rsupport.mobile1.test.utils

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.rsupport.mobile1.test.R
import com.rsupport.mobile1.test.adapter.BindAdapter

object Utils {

    const val DOMAIN = "https://www.gettyimages.com/photos/collaboration?"
    const val GETTY_IMAGE = "div.GalleryItems-module__searchContent___DbMmK div article a figure picture img"
    const val SUCCESS = 200

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun imageUrl(imageView: AppCompatImageView, imageUrl: String) {
        Glide.with(imageView.context)
            .load(imageUrl)
            .into(imageView)
    }

    @SuppressLint("NotifyDataSetChanged")
    @JvmStatic
    @BindingAdapter("items")
    fun <T> setItems(recyclerView: RecyclerView, items : ArrayList<T>?){
        if (recyclerView.adapter == null){
            val adapter = BindAdapter<T>()
            recyclerView.adapter = adapter
        }

        recyclerView.adapter?.let { adapter ->
            (adapter as BindAdapter<T>).run {
                setItems(items)
            }
            adapter.notifyDataSetChanged()
        }
    }
}