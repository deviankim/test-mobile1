package com.rsupport.mobile1.test.remote.datasource

import okhttp3.ResponseBody
import retrofit2.Response

interface DataSource {
    suspend fun getGettyList(): Response<ResponseBody>
}