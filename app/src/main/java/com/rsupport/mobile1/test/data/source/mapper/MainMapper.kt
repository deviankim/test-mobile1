package com.rsupport.mobile1.test.data.source.mapper

import com.rsupport.mobile1.test.data.model.main.MainDTO
import com.rsupport.mobile1.test.data.model.main.MainResponse
import com.rsupport.mobile1.test.domain.model.HtmlParseResult
import com.rsupport.mobile1.test.domain.model.MainItem
import com.rsupport.mobile1.test.domain.model.MainList

object MainMapper {

    fun HtmlParseResult<MainResponse>.toMainDomain(): HtmlParseResult<MainList> {
        return when (this) {
            is HtmlParseResult.Success -> {
                HtmlParseResult.Success(MainList(data.contents.map { it.toItem() }))
            }

            is HtmlParseResult.Error -> {
                HtmlParseResult.Error(exception)
            }
        }
    }

    private fun MainDTO.toItem(): MainItem {
        return MainItem(
            url = this.url,
            title = this.title
        )
    }
}