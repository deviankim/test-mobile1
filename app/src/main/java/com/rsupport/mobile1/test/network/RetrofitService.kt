package com.rsupport.mobile1.test.network

import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody
import retrofit2.http.GET

interface RetrofitService {

    @GET("photos/collaboration")
    fun fetchCollaborationImages(): Single<ResponseBody>
}