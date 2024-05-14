package com.rsupport.mobile1.test.network_api.datasource

import com.rsupport.mobile1.test.network_api.entity.Image

interface ImageDataSource {
    suspend fun getImage(page: Int): List<Image>
}