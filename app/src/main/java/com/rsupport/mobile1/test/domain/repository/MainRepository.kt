package com.rsupport.mobile1.test.domain.repository

import com.rsupport.mobile1.test.domain.model.HtmlParseResult
import com.rsupport.mobile1.test.domain.model.MainList
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    fun getMainList(): Flow<HtmlParseResult<MainList>>
}