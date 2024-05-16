package com.rsupport.mobile1.test.service

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface GettyService {
    @GET("/photos/collaboration")
    fun getGettyList(): Observable<Response<ResponseBody>>
}