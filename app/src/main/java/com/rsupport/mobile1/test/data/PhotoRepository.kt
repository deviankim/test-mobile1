package com.rsupport.mobile1.test.data

import com.rsupport.mobile1.test.data.domain.Photo
import com.rsupport.mobile1.test.data.domain.Response
import io.reactivex.rxjava3.core.Single

interface PhotoRepository {
    fun fetchCollaborationPhoto(page: Int): Single<Response<List<Photo>>>
}