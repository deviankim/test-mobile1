package com.rsupport.mobile1.test.data.remote

import io.reactivex.rxjava3.core.Single

interface PhotoRemoteDataSource {

    fun fetchCollaborationPhotoHtml(page: Int): Single<String>
}