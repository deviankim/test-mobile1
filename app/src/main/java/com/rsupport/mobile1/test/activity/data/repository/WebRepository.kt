package com.rsupport.mobile1.test.activity.data.repository

import com.rsupport.mobile1.test.activity.api.WebService
import javax.inject.Inject

class WebRepository @Inject constructor(private val webService: WebService) :
    BaseRepository() {

    suspend fun getPhotoCollaboration() = safeApiCall {
        webService.getPhotoCollaboration()
    }
}