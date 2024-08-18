package com.rsupport.mobile1.test.data.domain

import com.rsupport.mobile1.test.data.dto.PhotoDto

data class Photo(
    val name: String,
    val description: String,
    val thumbnailUrl: String
)

fun PhotoDto.asDomainModel() = Photo(name, description, thumbnailUrl)
