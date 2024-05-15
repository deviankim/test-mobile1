package com.blue.domain.repo

import com.blue.domain.model.PhotoData
import kotlinx.coroutines.flow.Flow

interface FavoriteRepo {
    fun getFavoriteRepo(): Flow<List<PhotoData>>
    suspend fun addFavoriteRepo(data: PhotoData)
    suspend fun deleteFavoriteRepo(id: Int)
}