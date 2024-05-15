package com.blue.domain.model

data class PhotoData(
    val photoId: Int,
    val photoURL: String,
    val favorite: Boolean,
    val title: String,
    val artist: String,
    val uploadDate: String,
)