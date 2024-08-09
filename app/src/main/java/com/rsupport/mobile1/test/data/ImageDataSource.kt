package com.rsupport.mobile1.test.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rsupport.mobile1.test.model.GettyItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class ImageDataSource() : PagingSource<Int, GettyItem>() {
    override fun getRefreshKey(state: PagingState<Int, GettyItem>): Int? {
        return state.anchorPosition?.let {
            val closestPageToPosition = state.closestPageToPosition(it)
            closestPageToPosition?.prevKey?.plus(1)
                ?: closestPageToPosition?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GettyItem> {
        val page = params.key ?: defaultStart

        return try {

            lateinit var doc: Document

            CoroutineScope(Dispatchers.IO).launch {
                doc =
                    Jsoup.connect("https://www.gettyimages.com/search/2/image?phrase=collaboration&sort=best&license=rf%2Crm&page=${page}")
                        .get()
            }.join()

            val items: List<GettyItem> = doc.select("picture").select("img[src]")
                .map { element -> GettyItem(element.attr("src")) }
 

            val prevKey = if (page == 1) null else page - 1
            val nextKey = if (items.isEmpty()) null else page + 1

            LoadResult.Page(items, prevKey, nextKey)


        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }


    companion object {
        const val defaultStart = 1
        const val defaultDisplay = 60
    }
}