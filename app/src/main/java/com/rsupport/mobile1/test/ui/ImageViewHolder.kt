package com.rsupport.mobile1.test.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rsupport.mobile1.test.R
import com.rsupport.mobile1.test.model.GettyItem
import com.rsupport.mobile1.test.databinding.ImageItemBinding

class ImageViewHolder(
    private val binding: ImageItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: GettyItem?) {
        item?.let{ item ->
            Glide.with(binding.root)
                .load(item.url)
                .centerCrop()
                .into(binding.imageView)
        }
    }

    companion object {
        fun create(
            parent: ViewGroup
        ): ImageViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.image_item, parent, false)
            val binding = ImageItemBinding.bind(view)
            return ImageViewHolder(binding)
        }
    }
}