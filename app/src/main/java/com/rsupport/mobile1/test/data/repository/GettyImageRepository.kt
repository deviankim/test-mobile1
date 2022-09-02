package com.rsupport.mobile1.test.data.repository

import com.rsupport.mobile1.test.data.DataSource
import com.rsupport.mobile1.test.data.GettyImage
import com.rsupport.mobile1.test.data.RemoteGettyImage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jsoup.select.Elements

class GettyImageRepository constructor(private val remoteGettyImage: RemoteGettyImage): DataSource{
    override suspend fun getGettyImage(page: Int): MutableList<GettyImage> {
        return remoteGettyImage.getGettyImage(page)
    }

}