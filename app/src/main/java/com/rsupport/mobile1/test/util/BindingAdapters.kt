package com.example.crawlin_test.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.crawlin_test.data.model.ImageData
import com.example.crawlin_test.ui.MainAdapter

object BindingAdapters {
    @BindingAdapter("bindImgList")
    @JvmStatic
    fun bindImgList(recyclerView: RecyclerView, items: List<ImageData>?) {

        if(recyclerView.adapter == null) {
            val adapter = MainAdapter()
            recyclerView.adapter = adapter
        }

        val myAdapter = recyclerView.adapter as MainAdapter

        // 자동 갱신
        myAdapter.submitList(items?.toMutableList())
    }


    @JvmStatic
    @BindingAdapter("visible")
    fun visible(view: View, value: Boolean) {
        view.visibility = when (value) {
            true -> View.VISIBLE
            else -> View.INVISIBLE
        }
    }

    @JvmStatic
    @BindingAdapter("show")
    fun show(view: TextView, value: String?) {
        if (!value.equals("")) {
            view.text = value
            view.visibility = View.VISIBLE
        } else {
            view.text = ""
            view.visibility = View.INVISIBLE
        }
    }

    // 이미지 바인딩
    @BindingAdapter("image")
    @JvmStatic
    fun image(imageView: ImageView, imageUrl: String) {
        ImageLoader.loadImage(imageUrl) { bitmap ->
            imageView.setImageBitmap(bitmap)
        }
    }


}
