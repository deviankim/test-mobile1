package com.rsupport.mobile1.test.activity.ui.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rsupport.mobile1.test.databinding.ItemLoadStateBinding

class MainLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<MainLoadStateAdapter.MainLoadStateViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): MainLoadStateViewHolder {
        return MainLoadStateViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MainLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState, retry)
    }

    class MainLoadStateViewHolder(private val binding: ItemLoadStateBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState, retry: () -> Unit) {
            binding.apply {
                btnRetry.setOnClickListener { retry() }
                progressBar.isVisible = loadState is LoadState.Loading
                groupError.isVisible = loadState is LoadState.Error
            }
        }

        companion object {
            fun from(parent: ViewGroup): MainLoadStateViewHolder {
                val binding = ItemLoadStateBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                return MainLoadStateViewHolder(binding)
            }
        }
    }
}