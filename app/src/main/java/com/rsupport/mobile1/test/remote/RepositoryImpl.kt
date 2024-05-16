package com.rsupport.mobile1.test.remote

import com.rsupport.mobile1.test.remote.datasource.DataSource
import okhttp3.ResponseBody
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val dataSource: DataSource): Repository {
    override suspend fun getGettyList(): ResponseBody {
        return MainMapper.mapperMain(dataSource.getGettyList().body()!!)
    }
}