package com.blue.domain.repo

import com.blue.domain.model.PhotoData
import kotlinx.coroutines.flow.Flow

interface FavoriteRepo {
    fun getFavoriteRepo(): Flow<List<PhotoData>>
    fun getFavoriteIdRepo(): Flow<List<Int>>
    suspend fun addFavoriteRepo(data: PhotoData)
    suspend fun deleteFavoriteRepo(photoId: Int)
}