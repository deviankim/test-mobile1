package com.rsupport.mobile1.test.activity.Data.Repository

import com.rsupport.mobile1.test.activity.Data.Entity.ImageEntity
import com.rsupport.mobile1.test.activity.Data.Entity.ImagesEntity

interface Repository {
    suspend fun getAllImageFromWeb(): List<ImageEntity>

    suspend fun saveImageToLocalDB(imageEntity: ImageEntity)

    suspend fun getAllImageFromLocalDB(): List<ImageEntity>

}