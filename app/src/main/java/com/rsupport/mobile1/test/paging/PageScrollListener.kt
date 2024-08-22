package com.rsupport.mobile1.test.paging

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PageScrollListener : RecyclerView.OnScrollListener() {

    var pagingEnable = false
    var preLoading = false
    var preLoadingCount = 0

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (recyclerView.computeVerticalScrollOffset() == 0) onTop(true) else onTop(false)

        if (pagingEnable) {
            recyclerView.layoutManager?.let {
                if (it.itemCount > 0) {
                    val lastVisibleItemPosition = (it as LinearLayoutManager).findLastCompletelyVisibleItemPosition()

                    if (preLoading) {
                        if (lastVisibleItemPosition > it.itemCount - preLoadingCount) {
                            onNextPaging()
                        }
                    } else {
                        if (lastVisibleItemPosition == it.itemCount - 1) {
                            onNextPaging()
                        }
                    }

                }
            }
        }
    }

    fun setPreLoading(count: Int) {
        this.preLoading = true
        this.preLoadingCount = count
    }

    abstract fun onNextPaging()

    abstract fun onTop(isTop: Boolean)
}