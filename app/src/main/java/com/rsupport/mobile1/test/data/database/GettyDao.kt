package com.rsupport.mobile1.test.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rsupport.mobile1.test.data.database.entities.GettyEntity


@Dao
interface GettyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGettyData(gettyEntity: GettyEntity)

    @Query("SELECT * FROM getty_image_table")
    suspend fun getGettyImage(): List<GettyEntity>
}