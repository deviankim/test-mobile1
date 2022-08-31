package com.rsupport.mobile1.test.data

import kotlinx.coroutines.flow.Flow

interface DataSource {
    fun getGettyImage(page: Int): Flow<MutableList<GettyImage>?>
}