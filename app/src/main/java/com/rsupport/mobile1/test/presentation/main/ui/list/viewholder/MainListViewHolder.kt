package com.rsupport.mobile1.test.presentation.main.ui.list.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.rsupport.mobile1.test.databinding.ItemMainListBinding
import com.rsupport.mobile1.test.domain.model.MainRecyclerViewItem

class MainListViewHolder(
    private val binding: ItemMainListBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: MainRecyclerViewItem.MainItem) {
        binding.item = item
    }
}