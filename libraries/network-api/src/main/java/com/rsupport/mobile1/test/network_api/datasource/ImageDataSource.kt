package com.rsupport.mobile1.test.network_api.datasource

interface ImageDataSource {
    suspend fun getImage(page: Int): List<String>
}