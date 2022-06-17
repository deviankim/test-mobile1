package com.rsupport.mobile1.test.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.rsupport.mobile1.test.utils.RemoteImageLoader

class GettyImageListAdapter : ListAdapter<String, GettyImageViewHolder>(COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GettyImageViewHolder {
        return GettyImageViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: GettyImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCurrentListChanged(
        previousList: MutableList<String>,
        currentList: MutableList<String>
    ) {
        RemoteImageLoader.clearCache()
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }
        }
    }
}