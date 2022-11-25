package com.rsupport.mobile1.test.data.pagingsource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rsupport.mobile1.test.domain.model.Image
import com.rsupport.mobile1.test.domain.repository.ImageRepository

class ImagePagingSource (
    private val imageRepository: ImageRepository
) : PagingSource<Int, Image>() {

    override fun getRefreshKey(state: PagingState<Int, Image>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Image> {
        return try {
            var page = params.key?: 1
            if(page < 1) page = 1

            val result = imageRepository.getImageList(page)
            val data = result.getOrThrow()

            LoadResult.Page(data = data, prevKey = null, nextKey = if(data.isEmpty()) null else page + 1)

        } catch (e: Exception) {
            Log.e("pagingSource Error", Log.getStackTraceString(e))
            LoadResult.Error(e)
        }
    }
}