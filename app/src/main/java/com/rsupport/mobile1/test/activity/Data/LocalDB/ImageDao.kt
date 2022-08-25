package com.rsupport.mobile1.test.activity.Data.LocalDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.rsupport.mobile1.test.activity.Data.Entity.ImageEntity

@Dao
interface ImageDao {

    @Query("SELECT * FROM ImageEntity")
    suspend fun getAll(): List<ImageEntity>

    @Insert
    suspend fun insertOne(ImageEntity: ImageEntity)

}