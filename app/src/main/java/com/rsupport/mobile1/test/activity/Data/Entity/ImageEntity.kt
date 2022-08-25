package com.rsupport.mobile1.test.activity.Data.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ImageEntity(
    @PrimaryKey(autoGenerate = true) val id : Long = 0,
    val url : String
)
