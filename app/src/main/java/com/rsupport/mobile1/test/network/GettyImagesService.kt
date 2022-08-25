package com.rsupport.mobile1.test.network

import com.rsupport.mobile1.test.model.dto.GettyImages
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface GettyImagesService {

    @GET("photos/collaboration")
    fun getPhotosCollaborationAsync(): Deferred<GettyImages>

}