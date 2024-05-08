package com.rsupport.mobile1.test.data.repository

import com.rsupport.mobile1.test.data.source.main.MainDataSource
import com.rsupport.mobile1.test.data.source.mapper.MainMapper.toMainDomain
import com.rsupport.mobile1.test.domain.model.HtmlParseResult
import com.rsupport.mobile1.test.domain.model.MainList
import com.rsupport.mobile1.test.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val mainDataSource: MainDataSource
) : MainRepository {

    override fun getMainList(pageNumber: Int): Flow<HtmlParseResult<MainList>> {
        return mainDataSource.getMainList(pageNumber).map { it.toMainDomain() }
    }
}