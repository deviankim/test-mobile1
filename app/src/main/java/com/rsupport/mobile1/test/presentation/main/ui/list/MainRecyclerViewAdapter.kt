package com.rsupport.mobile1.test.presentation.main.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rsupport.mobile1.test.databinding.ItemMainBottomBinding
import com.rsupport.mobile1.test.databinding.ItemMainListBinding
import com.rsupport.mobile1.test.domain.model.MainItem
import com.rsupport.mobile1.test.presentation.main.ui.list.viewholder.MainBottomViewHolder
import com.rsupport.mobile1.test.presentation.main.ui.list.viewholder.MainListViewHolder

class MainRecyclerViewAdapter : ListAdapter<MainItem, RecyclerView.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_LIST) {
            MainListViewHolder(
                ItemMainListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        } else {
            MainBottomViewHolder(
                ItemMainBottomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == VIEW_TYPE_LIST) {
            (holder as MainListViewHolder).bind(getItem(position))
        } else {
            (holder as MainBottomViewHolder).bind()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == currentList.size) VIEW_TYPE_BOTTOM else VIEW_TYPE_LIST
    }

    override fun getItemCount(): Int {
        return if (currentList.size == 0) 0 else currentList.size + 1
    }

    companion object {
        const val VIEW_TYPE_LIST = 0
        const val VIEW_TYPE_BOTTOM = 1

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