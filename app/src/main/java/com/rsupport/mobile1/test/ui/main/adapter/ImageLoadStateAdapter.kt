package com.rsupport.mobile1.test.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.rsupport.mobile1.test.R
import com.rsupport.mobile1.test.databinding.ItemLoadStateBinding

class ImageLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<ImageLoadStateAdapter.LoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding = DataBindingUtil.inflate<ItemLoadStateBinding>(LayoutInflater.from(parent.context), R.layout.item_load_state, parent, false)
        return LoadStateViewHolder(binding, retry)
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
        val layoutParams = holder.itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams
        layoutParams.isFullSpan = true
    }

    class LoadStateViewHolder(
        private val binding: ItemLoadStateBinding,
        private val retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(state: LoadState) {
            binding.isLoading = state is LoadState.Loading
            binding.isError = state is LoadState.Error
            binding.retryListener = View.OnClickListener { retry() }
            binding.executePendingBindings()
        }
    }

}