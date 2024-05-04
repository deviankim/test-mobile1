package com.rsupport.mobile1.test.presentation.main.ui.list.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.rsupport.mobile1.test.databinding.ItemImageImformationBinding
import com.rsupport.mobile1.test.domain.model.ImageList

class ImageListViewHolder(
    private val binding: ItemImageImformationBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ImageList) {
        binding.item = item
    }
}