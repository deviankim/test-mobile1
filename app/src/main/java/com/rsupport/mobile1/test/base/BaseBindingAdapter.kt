package com.rsupport.mobile1.test.base

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.rsupport.mobile1.test.ui.main.adapter.ImageLoadStateAdapter
import com.rsupport.mobile1.test.util.GlideApp

object BaseBindingAdapter {

    @JvmStatic
    @BindingAdapter("isVisible")
    fun View.bindVisible(isVisible: Boolean) {
        visibility = if(isVisible) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    @JvmStatic
    @BindingAdapter("loadImageFromURL")
    fun ImageView.loadImageFromURL(url: String) {
        GlideApp.with(this)
            .load(url)
            .override(SIZE_ORIGINAL)
//            .placeholder(R.color.black_alpha_20)
            .into(this)
    }

    @JvmStatic
    @BindingAdapter("initPagingAdapter")
    fun RecyclerView.bindPagingAdapter(pagingAdapter: PagingDataAdapter<*, *>) {
        if(this.adapter == null){
            pagingAdapter.apply {
                stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            }

            val combineAdapter = pagingAdapter.withLoadStateFooter(
                ImageLoadStateAdapter(pagingAdapter::retry)
            )

            val staggeredGridLayoutManager = StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL)
            staggeredGridLayoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS

            this.adapter = combineAdapter
            this.layoutManager = staggeredGridLayoutManager
            this.itemAnimator = null
        }
    }


}

