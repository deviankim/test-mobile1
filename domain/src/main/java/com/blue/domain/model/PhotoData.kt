package com.blue.domain.model

data class PhotoData(
    val id: Int,
    val photoUrl: String,
    val favorite: Boolean,
    val title: String,
    val artist: String,
    val uploadDate: String,
)