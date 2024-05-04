package com.rsupport.mobile1.test.presentation.main.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rsupport.mobile1.test.databinding.ItemImageImformationBinding
import com.rsupport.mobile1.test.domain.model.ImageList
import com.rsupport.mobile1.test.presentation.main.ui.list.viewholder.ImageListViewHolder

class ImageListAdapter : ListAdapter<ImageList, RecyclerView.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ImageListViewHolder(
            ItemImageImformationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ImageListViewHolder).bind(getItem(position))
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<ImageList>() {
            override fun areItemsTheSame(oldItem: ImageList, newItem: ImageList): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: ImageList, newItem: ImageList): Boolean {
                return oldItem == newItem
            }
        }
    }
}