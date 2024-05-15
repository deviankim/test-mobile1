package com.blue.data.repoImpl

import com.blue.data.local.dao.FavoriteDao
import com.blue.data.mapper.Mapper.asEntity
import com.blue.data.mapper.Mapper.asDomain
import com.blue.domain.model.PhotoData
import com.blue.domain.repo.FavoriteRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteRepoImpl @Inject constructor(
    private val dao: FavoriteDao
): FavoriteRepo {
    override fun getFavoriteRepo(): Flow<List<PhotoData>> =
        dao.getFavorite().map { it.asDomain() }

    override fun getFavoriteIdRepo(): Flow<List<Int>> =
        dao.getFavoriteID()

    override suspend fun addFavoriteRepo(data: PhotoData) {
        dao.addFavorite(data.asEntity())
    }

    override suspend fun deleteFavoriteRepo(photoId: Int) {
        dao.deleteFavorite(photoId)
    }
}