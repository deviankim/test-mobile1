package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.entity.GettyImage
import kotlinx.coroutines.flow.Flow

interface GettyImageRepository {

    fun getGettyImages(): Flow<PagingData<GettyImage>>
}