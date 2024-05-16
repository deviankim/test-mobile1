package com.blue.domain.repo

import com.blue.domain.model.PhotoData
import com.blue.domain.state.ResourceState
import kotlinx.coroutines.flow.Flow


interface PhotoRepo {
    suspend fun getPhotoData(): ResourceState<List<PhotoData>>
}