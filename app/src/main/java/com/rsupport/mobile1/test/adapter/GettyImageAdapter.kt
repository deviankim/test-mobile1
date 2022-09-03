package com.rsupport.mobile1.test.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rsupport.mobile1.test.data.GettyImage
import com.rsupport.mobile1.test.databinding.ListItemGettyImageBinding

class GettyImageAdapter : ListAdapter<
        GettyImage, GettyImageAdapter.ViewHolder>(GettyImageDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.run {
            image = getItem(position)
            executePendingBindings()
        }
    }

    class ViewHolder(val binding: ListItemGettyImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemGettyImageBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class GettyImageDiffCallback : DiffUtil.ItemCallback<GettyImage>() {
    override fun areItemsTheSame(oldItem: GettyImage, newItem: GettyImage): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: GettyImage, newItem: GettyImage): Boolean {
        return oldItem == newItem
    }
}