package com.rsupport.mobile1.test.adapter.footer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.rsupport.mobile1.test.databinding.ItemGettyLoadStateBinding

class GettyLoadStateAdapter(
    private val retryCallback: () -> Unit
) : LoadStateAdapter<GettyLoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): GettyLoadStateViewHolder {
        val binding = ItemGettyLoadStateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GettyLoadStateViewHolder(binding.root)
            .apply {
                binding.btnRetry.setOnClickListener {
                    retryCallback.invoke()
                }
            }
    }

    override fun onBindViewHolder(holder: GettyLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }
}