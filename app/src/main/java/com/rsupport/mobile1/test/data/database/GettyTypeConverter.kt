package com.rsupport.mobile1.test.data.database

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rsupport.mobile1.test.data.database.entities.GettyEntity

@TypeConverters
class GettyTypeConverter {

    private var gson = Gson()

    @TypeConverter
    fun toGettyString(data: GettyEntity): String? {
        return gson.toJson(data)
    }

    @TypeConverter
    fun fromGettyString(data: String?): GettyEntity? {
        val type = object : TypeToken<GettyEntity>() {}.type
        return gson.fromJson(data, type)
    }
}