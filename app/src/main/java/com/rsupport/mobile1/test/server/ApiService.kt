package com.rsupport.mobile1.test.server

import com.rsupport.mobile1.test.server.resp.RespSearchImages
import com.rsupport.mobile1.test.server.resp.RespSignIn
import io.reactivex.Single
import retrofit2.http.*

internal interface ApiService {
    //로그인
    @Headers(
        ApiConst.CONTENT_TYPE_SIGN_IN,
        ApiConst.API_KEY)
    @POST(ApiConst.API_SIGN_IN)
    fun signIn(): Single<RespSignIn>

    //이미지 검색
    @Headers(
        ApiConst.ACCEPT_SEARCH_IMAGES,
        ApiConst.API_KEY)
    @GET(ApiConst.API_SEARCH_IMAGES)
    fun searchImages(@Header("Authorization") token: String,
                     @Query("phrase") phrase: String,
                     @Query("page") page_num: Int,
                     @Query("page_size") page_size: Int): Single<RespSearchImages>
}