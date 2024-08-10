package com.rsupport.mobile1.test.activity.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rsupport.mobile1.test.databinding.ItemImageBinding

class ImageAdapter(private var imgUrlData: List<String>) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    class ImageViewHolder(val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imgUrl = imgUrlData[position]
        Glide.with(holder.binding.imageRowItem.context)
            .load(imgUrl)
            .into(holder.binding.imageRowItem)
    }

    override fun getItemCount() = imgUrlData.size
}
