package com.rsupport.mobile1.test.remote

import okhttp3.ResponseBody

interface Repository {
    suspend fun getGettyList(): ResponseBody
}