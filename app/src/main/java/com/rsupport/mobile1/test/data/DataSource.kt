package com.rsupport.mobile1.test.data

import kotlinx.coroutines.flow.Flow

interface DataSource {
   suspend fun getGettyImage(page: Int): MutableList<GettyImage>?
}