package com.example.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.model.GettyImageDto

class GettyImagePagingSource(
    private val loader: suspend (Int) -> Result<List<GettyImageDto>>
) : PagingSource<Int, GettyImageDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GettyImageDto> {
        val page = params.key ?: INITIAL_PAGE_INDEX

        val result = loader.invoke(page)

        val data = result.getOrElse { exception ->
            return LoadResult.Error(exception)
        }

        return if (data.isNotEmpty()) {
            LoadResult.Page(
                data = data,
                prevKey = if (page == INITIAL_PAGE_INDEX) null else page - 1,
                nextKey = page + 1
            )
        } else {
            LoadResult.Error(NoSuchElementException("result is Empty"))
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GettyImageDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val INITIAL_PAGE_INDEX = 1
    }
}
