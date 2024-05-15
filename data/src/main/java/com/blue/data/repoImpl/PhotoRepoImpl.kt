package com.blue.data.repoImpl

import android.util.Log
import com.blue.data.local.dao.FavoriteDao
import com.blue.data.remote.datasource.PhotoDataSourceImpl
import com.blue.data.mapper.Mapper.asDomain
import com.blue.domain.model.PhotoData
import com.blue.domain.repo.PhotoRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PhotoRepoImpl @Inject constructor(
    private val photoDataSource: PhotoDataSourceImpl,
    private val favoriteDao: FavoriteDao
): PhotoRepo {
    override fun getPhotoData(): Flow<List<PhotoData>> {
        return favoriteDao.getFavoriteID().map { idList ->
            val favoriteId = idList.toMutableList()
            photoDataSource.getPhotoDataSource().map { data ->
                val id = data.id?.toInt() ?: -1
                data.asDomain(favoriteId.contains(id))
            }
        }
//        return flow {
//            val favoriteId = favoriteDao.getFavoriteID().first().map { it.photoId }.toMutableList()
//            Log.e("TAG", "getPhotoData: $favoriteId", )
//            val photoData = photoDataSource.getPhotoDataSource().map {
//                val id = it.id?.toInt() ?: -1
//                it.asDomain(favoriteId.contains(id))
//            }
//            emit(photoData)
//        }
    }
}