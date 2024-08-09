package com.rsupport.mobile1.test.ui

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.rsupport.mobile1.test.model.GettyItem

class ImageAdapter() : PagingDataAdapter<GettyItem, ImageViewHolder>(comparator) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImageViewHolder {
        return ImageViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object {
        val comparator = object : DiffUtil.ItemCallback<GettyItem>() {
            override fun areItemsTheSame(oldItem: GettyItem, newItem: GettyItem): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(oldItem: GettyItem, newItem: GettyItem): Boolean {
                return oldItem == newItem
            }

        }
    }
}