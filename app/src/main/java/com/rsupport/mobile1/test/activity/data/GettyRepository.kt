package com.rsupport.mobile1.test.activity.data

import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

class GettyRepository @Inject constructor(
    private val service: GettyImageCrawlingService
) {
    val gettySearchResultStream = MutableSharedFlow<List<String>>(1)

    suspend fun searchImage(query: String): Boolean {
        service.getThumbnailUrlList(query)
            .onSuccess { gettySearchResultStream.emit(it) }
            .also { return it.isSuccess }
    }
}