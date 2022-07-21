package com.rsupport.mobile1.test.activity.domain

import com.rsupport.mobile1.test.activity.data.GettyImage
import kotlinx.coroutines.Deferred

interface GettyImageRepository {
    suspend fun getImage(): List<GettyImage>?
}