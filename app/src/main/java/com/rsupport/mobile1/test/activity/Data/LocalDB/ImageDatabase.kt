package com.rsupport.mobile1.test.activity.Data.LocalDB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rsupport.mobile1.test.activity.Data.Entity.ImageEntity

@Database(entities = [ImageEntity::class], version = 1, exportSchema = false)
abstract class ImageDatabase : RoomDatabase() {
    companion object {
        const val DB_NAME = "Image.db"
    }

    abstract fun Dao(): ImageDao
}