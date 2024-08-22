package com.rsupport.mobile1.test.network

import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface RetrofitService {

    @GET("photos/collaboration")
    fun fetchCollaborationPhotoHtml(
        @QueryMap params: Map<String, String>
    ): Single<Response<ResponseBody>>
}