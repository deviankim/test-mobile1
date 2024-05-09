package com.rsupport.mobile1.test.presentation.main.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rsupport.mobile1.test.R
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
        return if (viewType == R.layout.item_main_list) {
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
        when (val item = getItem(position)) {
            is MainRecyclerViewItem.MainItem -> (holder as MainListViewHolder).bind(item)
            is MainRecyclerViewItem.PageNumber -> (holder as MainBottomViewHolder).bind(item)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is MainRecyclerViewItem.MainItem -> R.layout.item_main_list
            is MainRecyclerViewItem.PageNumber -> R.layout.item_main_bottom
        }
    }

    companion object {
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