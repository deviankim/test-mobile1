package com.blue.data.repoImpl

import com.blue.data.local.dao.FavoriteDao
import com.blue.data.remote.datasource.PhotoDataSourceImpl
import com.blue.data.mapper.Mapper.asDomain
import com.blue.domain.model.PhotoData
import com.blue.domain.repo.PhotoRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PhotoRepoImpl @Inject constructor(
    private val photoDataSource: PhotoDataSourceImpl,
    private val favoriteDao: FavoriteDao
): PhotoRepo {
    override suspend fun getPhotoData(): Flow<List<PhotoData>> {
        val favoriteId = favoriteDao.getFavoriteID().first().map { it.photoId }.toMutableList()
        return flow {
            photoDataSource.getPhotoDataSource().map {
                val id = it.id?.toInt() ?: -1
                it.asDomain(favoriteId.contains(id))
            }
        }
    }
}