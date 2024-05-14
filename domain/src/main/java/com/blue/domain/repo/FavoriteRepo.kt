package com.blue.domain.repo

interface FavoriteRepo {
    suspend fun addFavoriteRepo(id: Int)
    suspend fun deleteFavoriteRepo(id: Int)
}