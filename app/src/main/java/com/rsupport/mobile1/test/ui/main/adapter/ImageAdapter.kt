package com.rsupport.mobile1.test.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.rsupport.mobile1.test.R
import com.rsupport.mobile1.test.databinding.ListItemImageBinding
import com.rsupport.mobile1.test.domain.model.Image

class ImageAdapter : PagingDataAdapter<Image, ImageAdapter.ImageViewHolder>(ImageDiffCallback()) {

    class ImageViewHolder(
        private val binding: ListItemImageBinding
    ) : ViewHolder(binding.root) {

        fun bindImage(image: Image) {
            binding.image = image
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = DataBindingUtil.inflate<ListItemImageBinding>(
            LayoutInflater.from(parent.context),
            R.layout.list_item_image, parent,
            false
        )

        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bindImage(it)
        }
    }


}

private class ImageDiffCallback : DiffUtil.ItemCallback<Image>() {
    override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem == newItem
    }

}