package com.rsupport.mobile1.test.activity.ui.common

import androidx.recyclerview.widget.DiffUtil
import com.rsupport.mobile1.test.activity.data.model.ImageInformation

class ImageListDiffUtil : DiffUtil.ItemCallback<ImageInformation>() {
    override fun areItemsTheSame(oldItem: ImageInformation, newItem: ImageInformation): Boolean {
        return oldItem.imageUrl == newItem.imageUrl
    }

    override fun areContentsTheSame(oldItem: ImageInformation, newItem: ImageInformation): Boolean {
        return oldItem == newItem
    }
}