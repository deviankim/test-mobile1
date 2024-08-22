package com.rsupport.mobile1.test.data.remote

import com.rsupport.mobile1.test.network.RetrofitService
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class PhotoRemoteDataSourceImpl @Inject constructor(private val service: RetrofitService): PhotoRemoteDataSource {
    override fun fetchCollaborationPhotoHtml(page: Int): Single<String> {
        val params = HashMap<String, String>()
        params["page"] = page.toString()

        return service.fetchCollaborationPhotoHtml(params)
            .map { response -> response.body()?.string() ?: "" }
            .subscribeOn(Schedulers.io())
    }
}