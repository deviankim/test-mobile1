package com.rsupport.mobile1.test.data


interface DataSource {
    suspend fun getGettyImage(page: Int): MutableList<GettyImage>?
}