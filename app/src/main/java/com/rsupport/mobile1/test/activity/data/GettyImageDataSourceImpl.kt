package com.rsupport.mobile1.test.activity.data

import javax.inject.Inject

class GettyImageDataSourceImpl @Inject constructor(private val webHelper: WebHelper): GettyImageDataSource {
    override suspend fun requestGettyImage(): List<GettyImage>? =
        webHelper.requestGettyImage()
}