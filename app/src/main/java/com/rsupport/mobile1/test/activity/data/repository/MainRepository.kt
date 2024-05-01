package com.rsupport.mobile1.test.activity.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.rsupport.mobile1.test.activity.data.model.ImageListPagingSource
import com.rsupport.mobile1.test.activity.data.network.WebScrapper
import javax.inject.Inject

class MainRepository @Inject constructor(private val webScrapper: WebScrapper) {

    fun getImageList(pageSize: Int) = Pager(
        PagingConfig(
            pageSize = pageSize,
            initialLoadSize = pageSize,
            maxSize = 5
        )
    ) {
        ImageListPagingSource(
            webScrapper
        )
    }.flow
}