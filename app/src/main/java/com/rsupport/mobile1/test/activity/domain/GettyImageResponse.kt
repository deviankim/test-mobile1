package com.rsupport.mobile1.test.activity.domain

import com.rsupport.mobile1.test.activity.data.GettyImage

data class GettyImageResponse(
    val assetId: String = "",
    val author: String = "",
    val caption: String = "",
    val contentUrl: String = "",
    val creditText: String = "",
    val description: String = "",
    val name: String = "",
    val thumbnailUrl: String = "",
    val uploadDate: String = ""
)

fun GettyImage.mapper() = GettyImageResponse(
    assetId = this.assetId,
    author = this.author,
    caption = this.caption,
    contentUrl = this.contentUrl,
    creditText = this.creditText,
    description = this.description,
    name = this.name,
    thumbnailUrl = this.thumbnailUrl,
    uploadDate = this.uploadDate
)
