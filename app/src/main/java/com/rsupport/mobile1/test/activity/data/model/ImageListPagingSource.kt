package com.rsupport.mobile1.test.activity.data.model

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rsupport.mobile1.test.activity.data.network.WebScrapper

class ImageListPagingSource(private val webScrapper: WebScrapper) :
    PagingSource<Int, ImageInformation>() {

    override fun getRefreshKey(state: PagingState<Int, ImageInformation>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageInformation> {
        val pageId = params.key ?: 1

        return try {
            val result = webScrapper.getImageInformation(pageId)
            if (result.isSuccess) {
                val data = result.getOrDefault(emptyList())
                LoadResult.Page(
                    data = data,
                    prevKey = if (pageId < 1) null else pageId - 1,
                    nextKey = if (data.isEmpty()) null else pageId + 1
                )
            } else {
                val exception = result.exceptionOrNull() ?: Exception("Unknown error")
                throw (exception)
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}