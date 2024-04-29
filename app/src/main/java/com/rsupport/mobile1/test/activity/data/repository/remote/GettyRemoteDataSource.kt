package com.rsupport.mobile1.test.activity.data.repository.remote

import org.jsoup.nodes.Element

interface GettyRemoteDataSource {
    suspend fun getGettyItem(url: String): Element
}