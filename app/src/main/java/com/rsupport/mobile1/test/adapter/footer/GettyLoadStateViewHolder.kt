package com.rsupport.mobile1.test.adapter.footer

import android.view.View
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.rsupport.mobile1.test.databinding.ItemGettyLoadStateBinding
import com.rsupport.mobile1.test.extension.parseError

class GettyLoadStateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding = ItemGettyLoadStateBinding.bind(itemView)

    fun bind(loadState: LoadState) {
        if (itemView.layoutParams is StaggeredGridLayoutManager.LayoutParams) {
            (itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams).isFullSpan = true
        }

        with(binding) {
            pbGetty.isVisible = loadState is LoadState.Loading
            llError.isVisible = loadState is LoadState.Error

            if (loadState is LoadState.Error) {
                tvErrorMsg.text = loadState.error.parseError(itemView.context)
            }
        }
    }
}
