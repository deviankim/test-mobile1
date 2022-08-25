package com.rsupport.mobile1.test.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rsupport.mobile1.test.util.Constants.GETTY_IMAGE_TABLE

@Entity(tableName = GETTY_IMAGE_TABLE)
data class GettyEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val author: String?,
    val date: String,
    val thumbnailUrl: String?
)
