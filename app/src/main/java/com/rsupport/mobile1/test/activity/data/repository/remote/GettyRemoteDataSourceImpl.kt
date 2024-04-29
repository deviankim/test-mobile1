package com.rsupport.mobile1.test.activity.data.repository.remote

import com.rsupport.mobile1.test.activity.data.api.GettyService
import org.jsoup.nodes.Element
import javax.inject.Inject

class GettyRemoteDataSourceImpl @Inject constructor(private val gettyService: GettyService): GettyRemoteDataSource {
    override suspend fun getGettyItem(url: String): Element {
        return gettyService.getGettyItem(url)
    }
}