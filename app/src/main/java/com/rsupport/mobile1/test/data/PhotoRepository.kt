package com.rsupport.mobile1.test.data

import com.rsupport.mobile1.test.data.domain.Photo
import io.reactivex.rxjava3.core.Single

interface PhotoRepository {
    fun fetchCollaborationPhoto(): Single<List<Photo>>
}