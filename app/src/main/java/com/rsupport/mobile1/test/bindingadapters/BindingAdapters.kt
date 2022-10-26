package com.rsupport.mobile1.test.bindingadapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rsupport.mobile1.test.adapter.ImageListAdapter
import com.rsupport.mobile1.test.server.resp.ImageData

object BindingAdapters {
    //Glide로 이미지뷰에 이미지를 출력하는 메서드
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImage(imageView: ImageView, url: String?) {
        if (url != null) {
            Glide.with(imageView.context).load(url).into(imageView)
        } else {
            imageView.setImageResource(android.R.color.transparent)
        }
    }

    //간단한 공지사항 리스트를 리사이클러뷰 어댑터에 연결하는 메서드
    @JvmStatic
    @BindingAdapter("imageList")
    fun bindImageListAdapter(view: RecyclerView, itemList: ArrayList<ImageData>?) {
        var simpleImageDataList: ArrayList<ImageData> = ArrayList<ImageData>()
        itemList?.let {
            simpleImageDataList = itemList
        }
        var adapter: ImageListAdapter = view.adapter as ImageListAdapter
        adapter.setList(simpleImageDataList)
    }
}