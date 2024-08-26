package com.rsupport.mobile1.test.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.domain.entity.GettyImage
import com.rsupport.mobile1.test.R

class GettyImageAdapter : PagingDataAdapter<GettyImage, GettyImageViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GettyImageViewHolder {
        return GettyImageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_getty_image, parent, false))
    }

    override fun onBindViewHolder(holder: GettyImageViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<GettyImage>() {
            override fun areItemsTheSame(oldItem: GettyImage, newItem: GettyImage): Boolean =
                oldItem.imageUrl == newItem.imageUrl

            override fun areContentsTheSame(oldItem: GettyImage,newItem: GettyImage): Boolean =
                oldItem == newItem
        }
    }
}