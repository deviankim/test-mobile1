package com.rsupport.mobile1.test.activity.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rsupport.mobile1.test.activity.utils.RemoteImageLoader
import com.rsupport.mobile1.test.databinding.ListItemGettyImageBinding

class GettyImageViewHolder(
    private val binding: ListItemGettyImageBinding
) : RecyclerView.ViewHolder(binding.root) {

    private val imageLoader = RemoteImageLoader()

    fun bind(imageUrl: String) {
        imageLoader.loadRemoteImage(binding.imageView, imageUrl)
    }

    companion object {
        fun create(parent: ViewGroup): GettyImageViewHolder {
            val binding = ListItemGettyImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return GettyImageViewHolder(binding)
        }
    }
}