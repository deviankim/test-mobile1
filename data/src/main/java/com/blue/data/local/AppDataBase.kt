package com.blue.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.blue.data.local.dao.FavoriteDao
import com.blue.data.local.entity.FavoriteEntity

@Database(
    entities = [FavoriteEntity::class],
    version = 1,
    exportSchema = true
)

abstract class AppDataBase: RoomDatabase() {
    abstract fun FavoriteDao(): FavoriteDao
}