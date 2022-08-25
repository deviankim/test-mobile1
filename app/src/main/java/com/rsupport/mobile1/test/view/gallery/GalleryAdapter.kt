package com.rsupport.mobile1.test.view.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rsupport.mobile1.test.databinding.ItemGalleryBinding
import com.rsupport.mobile1.test.model.ui.GalleryItem

class MainAdapter : ListAdapter<GalleryItem, MainAdapter.ViewHolder>(GalleryComparator) {

    class ViewHolder(val binding: ItemGalleryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ItemGalleryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ).let {
            ViewHolder(it)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.run {
            item = getItem(position)
            executePendingBindings()
        }
    }
}

object GalleryComparator : DiffUtil.ItemCallback<GalleryItem>() {
    override fun areItemsTheSame(oldItem: GalleryItem, newItem: GalleryItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: GalleryItem, newItem: GalleryItem): Boolean {
        return oldItem == newItem
    }
}