package com.rsupport.mobile1.test.model.ui

data class GalleryUiModel(
    val items: List<GalleryItem>? = null
)

data class GalleryItem(
    val artist: String,
    val collectionName: String,
    val family: String,
    val id: String,
    val shortTitle: String,
    val licenseType: String,
    val thumbnailInfo: ThumbnailInfo
)

data class ThumbnailInfo(
    val thumbUrl: String,
    val thumbnailSize: Int
) {
    constructor(thumbUrl: String, thumbnailSize: Float) : this(thumbUrl, thumbnailSize.toInt())
}
