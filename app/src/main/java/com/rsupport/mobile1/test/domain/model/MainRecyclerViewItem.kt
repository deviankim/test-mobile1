package com.rsupport.mobile1.test.domain.model

sealed class MainRecyclerViewItem {
    data class MainItem(val url: String, val title: String) : MainRecyclerViewItem()
    data class PageNumber(val pageNumber: Int) : MainRecyclerViewItem()
}