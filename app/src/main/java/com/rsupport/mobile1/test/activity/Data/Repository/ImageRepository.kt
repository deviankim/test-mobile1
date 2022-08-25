package com.rsupport.mobile1.test.activity.Data.Repository


import android.util.Log
import com.rsupport.mobile1.test.activity.Data.Entity.ImageEntity
import com.rsupport.mobile1.test.activity.Data.LocalDB.ImageDao
import com.rsupport.mobile1.test.activity.Data.Entity.ImagesEntity
import com.rsupport.mobile1.test.activity.Data.Network.ImageDBProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class ImageRepository(
    private val IODispatcher: CoroutineDispatcher,
    private val imageDao: ImageDao,
) : Repository {
    override suspend fun getAllImageFromWeb(): List<ImageEntity> = withContext(IODispatcher) {
        return@withContext ImageDBProvider.getAllImages()
    }

    override suspend fun saveImageToLocalDB(imageEntity: ImageEntity) = withContext(IODispatcher) {
        imageDao.insertOne(imageEntity)
    }

    override suspend fun getAllImageFromLocalDB(): List<ImageEntity> = withContext(IODispatcher) {
        imageDao.getAll()
    }

}