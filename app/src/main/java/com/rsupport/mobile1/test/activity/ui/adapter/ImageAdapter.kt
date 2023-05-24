package com.rsupport.mobile1.test.activity.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.rsupport.mobile1.test.R
import com.rsupport.mobile1.test.activity.data.vo.ImageData
import com.rsupport.mobile1.test.activity.util.BindingViewHolder
import com.rsupport.mobile1.test.activity.viewmodel.MainViewModel
import com.rsupport.mobile1.test.databinding.ItemImageBinding

class ImageAdapter() :
    ListAdapter<ImageData, ImageAdapter.ViewHolder>(TaskDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_image,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ImageAdapter.ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    inner class ViewHolder(view: View) : BindingViewHolder<ItemImageBinding>(view) {
        fun bind(item: ImageData) {
            binding.item = item
        }
    }

    class TaskDiffCallback : DiffUtil.ItemCallback<ImageData>() {
        override fun areItemsTheSame(oldItem: ImageData, newItem: ImageData): Boolean {
            return oldItem.index == newItem.index
        }

        override fun areContentsTheSame(oldItem: ImageData, newItem: ImageData): Boolean {
            return oldItem == newItem
        }

        override fun getChangePayload(oldItem: ImageData, newItem: ImageData): Any? {
            return super.getChangePayload(oldItem, newItem)
        }
    }
}