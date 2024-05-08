package com.rsupport.mobile1.test.data.source.mapper

import com.rsupport.mobile1.test.data.model.main.MainDTO
import com.rsupport.mobile1.test.data.model.main.MainResponse
import com.rsupport.mobile1.test.domain.model.HtmlParseResult
import com.rsupport.mobile1.test.domain.model.MainRecyclerViewItem
import com.rsupport.mobile1.test.domain.model.MainList

object MainMapper {

    fun HtmlParseResult<MainResponse>.toMainDomain(): HtmlParseResult<MainList> {
        return when (this) {
            is HtmlParseResult.Success -> {
                val mainItems = data.contents.map { it.toItem() }
                val combinedList = mainItems + MainRecyclerViewItem.PageNumber(pageNumber = 1)
                HtmlParseResult.Success(MainList(combinedList))
            }

            is HtmlParseResult.Error -> {
                HtmlParseResult.Error(exception)
            }
        }
    }

    private fun MainDTO.toItem(): MainRecyclerViewItem.MainItem {
        return MainRecyclerViewItem.MainItem(
            url = this.url,
            title = this.title
        )
    }
}