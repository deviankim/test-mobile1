package com.example.data.model

import com.example.domain.entity.GettyImage

data class GettyImageDto(
    val imageUrl: String
) {
    fun toEntity(): GettyImage {
        return GettyImage(
            imageUrl = imageUrl
        )
    }
}