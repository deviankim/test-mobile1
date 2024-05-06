package com.rsupport.mobile1.test.presentation.main.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rsupport.mobile1.test.databinding.ItemImageImformationBinding
import com.rsupport.mobile1.test.domain.model.MainItem
import com.rsupport.mobile1.test.presentation.main.ui.list.viewholder.MainListViewHolder

class MainListAdapter : ListAdapter<MainItem, RecyclerView.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MainListViewHolder(
            ItemImageImformationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MainListViewHolder).bind(getItem(position))
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<MainItem>() {
            override fun areItemsTheSame(oldItem: MainItem, newItem: MainItem): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: MainItem, newItem: MainItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}