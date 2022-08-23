package com.rsupport.mobile1.test.model.dto

data class GettyImages(
    val gallery: Gallery
)

data class Gallery(
    val pageSize: Int,
    val assets: List<Asset>,
)

data class Asset(
    val agreement: Any,
    val agreementDetails: List<Any>,
    val agreements: List<Any>,
    val altText: String,
    val artist: String,
    val assetType: String,
    val caption: String,
    val cirActionCount: Any,
    val clipLength: Any,
    val collapsedCount: Any,
    val collectionCode: String,
    val collectionName: String,
    val colorIndexes: List<String>,
    val contributorDisplayName: Any,
    val contributorMemberName: Any,
    val dateCreated: String,
    val dateSubmitted: String,
    val editorialOnly: Boolean,
    val eventDate: Any,
    val eventId: Any,
    val eventName: Any,
    val family: String,
    val filmDefinition: Any,
    val filmPreviewUrl: Any,
    val id: String,
    val is360: Boolean,
    val isAnalogArchive: Boolean,
    val isCallForImage: Boolean,
    val isEmbeddable: Boolean,
    val isExclusiveContent: Boolean,
    val isIstockSignature: Boolean,
    val isOffline: Boolean,
    val istockCollection: Any,
    val landingUrl: String,
    val licenseType: String,
    val maxDimensions: MaxDimensions,
    val mediaType: String,
    val orientation: String,
    val people: String,
    val quality: String,
    val releaseCode: String,
    val seoCaption: String,
    val seoDuration: Any,
    val seoTitle: String,
    val shortTitle: String,
    val slot: Int,
    val thumbUrl: String,
    val title: String,
    val topKeywords: Any,
    val uploadDate: String,
    val usageInfo: String
)

data class MaxDimensions(
    val height: Int,
    val width: Int
)