package com.rsupport.mobile1.test.data.source.main

import com.rsupport.mobile1.test.data.model.main.MainResponse
import com.rsupport.mobile1.test.domain.model.HtmlParseResult
import kotlinx.coroutines.flow.Flow

interface MainDataSource {

    fun getMainList(): Flow<HtmlParseResult<MainResponse>>
}