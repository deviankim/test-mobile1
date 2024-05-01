package com.rsupport.mobile1.test.activity.ui.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rsupport.mobile1.test.activity.data.model.ImageInformation
import com.rsupport.mobile1.test.activity.ui.common.ImageListDiffUtil
import com.rsupport.mobile1.test.activity.util.Constants
import com.rsupport.mobile1.test.databinding.ItemImageInformationBinding

class MainPagingAdapter :
    PagingDataAdapter<ImageInformation, MainPagingAdapter.MainViewHolder>(ImageListDiffUtil()) {

    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount) {
            Constants.CONTENTS_TYPE
        } else {
            Constants.LOAD_STATE_TYPE
        }
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder.from(parent)
    }

    class MainViewHolder(private val binding: ItemImageInformationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ImageInformation) {
            binding.imageInformation = item
        }

        companion object {
            fun from(parent: ViewGroup): MainViewHolder {
                val binding = ItemImageInformationBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                return MainViewHolder(binding)
            }
        }
    }
}