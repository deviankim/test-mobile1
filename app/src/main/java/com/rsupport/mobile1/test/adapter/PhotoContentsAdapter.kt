package com.rsupport.mobile1.test.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rsupport.mobile1.test.data.domain.Photo
import com.rsupport.mobile1.test.databinding.ViewHolderPhotoBinding
import com.rsupport.mobile1.test.image.ImageLoader

class PhotoContentsAdapter: ListAdapter<Photo, RecyclerView.ViewHolder>(PhotoContentsDiff) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PhotoViewHolder(ViewHolderPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PhotoViewHolder) {
            holder.bind(getItem(position))
        }
    }

    class PhotoViewHolder(private val binding: ViewHolderPhotoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Photo) {
            binding.viewHolderPhotoTitle.text = item.name
            binding.viewHolderPhotoDescription.text = item.description

            ImageLoader.load(binding.viewHolderPhotoImage, item.thumbnailUrl)
        }
    }
}

object PhotoContentsDiff: DiffUtil.ItemCallback<Photo>() {
    override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem == newItem
    }
}

