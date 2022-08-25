package com.rsupport.mobile1.test.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rsupport.mobile1.test.data.database.entities.GettyEntity


@Database(
    entities = [GettyEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(GettyTypeConverter::class)
abstract class GettyDatabase: RoomDatabase() {
    abstract fun gettyDao(): GettyDao

}