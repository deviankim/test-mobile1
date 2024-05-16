package com.rsupport.mobile1.test.remote.datasource

import com.rsupport.mobile1.test.service.GettyService
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class DataSourceImpl @Inject constructor(private val gettyService: GettyService) : DataSource {
    override suspend fun getGettyList(): Response<ResponseBody> {
        return gettyService.getGettyList()
    }
}