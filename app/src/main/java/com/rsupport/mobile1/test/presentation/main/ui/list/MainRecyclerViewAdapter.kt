package com.rsupport.mobile1.test.presentation.main.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rsupport.mobile1.test.databinding.ItemMainBottomBinding
import com.rsupport.mobile1.test.databinding.ItemMainListBinding
import com.rsupport.mobile1.test.domain.model.MainRecyclerViewItem
import com.rsupport.mobile1.test.presentation.main.ui.list.viewholder.MainBottomViewHolder
import com.rsupport.mobile1.test.presentation.main.ui.list.viewholder.MainListViewHolder

class MainRecyclerViewAdapter(
    private val mainBottomListener: MainBottomListener,
) : ListAdapter<MainRecyclerViewItem, RecyclerView.ViewHolder>(diffCallback) {

    interface MainBottomListener {
        fun goToPreviousPage()
        fun goToNextPage()
        fun goToSetPage(pageNumber: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_LIST) {
            MainListViewHolder(
                ItemMainListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        } else {
            MainBottomViewHolder(
                ItemMainBottomBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                mainBottomListener
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            VIEW_TYPE_LIST -> {
                val item = getItem(position) as MainRecyclerViewItem.MainItem
                (holder as MainListViewHolder).bind(item)
            }

            VIEW_TYPE_BOTTOM -> {
                val item = getItem(position) as MainRecyclerViewItem.PageNumber
                (holder as MainBottomViewHolder).bind(item)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is MainRecyclerViewItem.MainItem -> VIEW_TYPE_LIST
            is MainRecyclerViewItem.PageNumber -> VIEW_TYPE_BOTTOM
        }
    }

    companion object {
        const val VIEW_TYPE_LIST = 0
        const val VIEW_TYPE_BOTTOM = 1

        private val diffCallback = object : DiffUtil.ItemCallback<MainRecyclerViewItem>() {
            override fun areItemsTheSame(
                oldItem: MainRecyclerViewItem,
                newItem: MainRecyclerViewItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: MainRecyclerViewItem,
                newItem: MainRecyclerViewItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}