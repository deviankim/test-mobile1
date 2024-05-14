package com.blue.data.repoImpl

import com.blue.data.local.dao.FavoriteDao
import com.blue.data.local.entity.FavoriteEntity
import com.blue.domain.repo.FavoriteRepo
import javax.inject.Inject

class FavoriteRepoImpl @Inject constructor(
    private val dao: FavoriteDao
): FavoriteRepo {
    override suspend fun addFavoriteRepo(id: Int) {
        dao.addFavorite(FavoriteEntity())
    }

    override suspend fun deleteFavoriteRepo(id: Int) {
        dao.deleteFavorite(id)
    }

}