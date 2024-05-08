package com.rsupport.mobile1.test.data.source.mapper

import com.rsupport.mobile1.test.data.model.main.MainDTO
import com.rsupport.mobile1.test.data.model.main.MainResponse
import com.rsupport.mobile1.test.domain.model.HtmlParseResult
import com.rsupport.mobile1.test.domain.model.MainList
import com.rsupport.mobile1.test.domain.model.MainRecyclerViewItem

object MainMapper {

    fun HtmlParseResult<MainResponse>.toMainDomain(): HtmlParseResult<MainList> {
        return when (this) {
            is HtmlParseResult.Success -> HtmlParseResult.Success(data.toMainList())
            is HtmlParseResult.Error -> HtmlParseResult.Error(exception)
        }
    }

    private fun MainResponse.toMainList(): MainList {
        val mainItems = contents.map { it.toItem() }
        val combinedList = mainItems + MainRecyclerViewItem.PageNumber(pageNumber = 1)
        return MainList(combinedList)
    }

    private fun MainDTO.toItem(): MainRecyclerViewItem.MainItem =
        MainRecyclerViewItem.MainItem(
            url = this.url,
            title = this.title
        )
}