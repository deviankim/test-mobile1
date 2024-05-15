package com.blue.data.mapper

import com.blue.data.local.entity.FavoriteEntity
import com.blue.data.remote.model.Dimensions
import com.blue.data.remote.model.ServerResponse
import com.blue.domain.model.PhotoData
import org.json.JSONObject

object Mapper {
    fun List<JSONObject>.asResponse(): List<ServerResponse> {
        return this.map {
            ServerResponse(
                altText = it.optString("altText"),
                id = it.optString("id"),
                assetId = it.optString("assetId"),
                agreement = it.optString("agreement"),
                agreements = it.optJSONArray("agreements")?.let { jsonArray ->
                    List(jsonArray.length()) { jsonArray.get(it) }
                },
                agreementDetails = it.optJSONArray("agreementDetails")?.let { jsonArray ->
                    List(jsonArray.length()) { jsonArray.get(it) }
                },
                assetType = it.optString("assetType"),
                family = it.optString("family"),
                editorialOnly = it.optBoolean("editorialOnly"),
                licenseType = it.optString("licenseType"),
                quality = it.optString("quality"),
                clipLength = it.optString("clipLength"),
                cirActionCount = it.optString("cirActionCount"),
                filmDefinition = it.optString("filmDefinition"),
                seoDuration = it.optString("seoDuration"),
                filmPreviewUrl = it.optString("filmPreviewUrl"),
                istockCollection = it.optString("istockCollection"),
                editorialProducts = it.optString("editorialProducts"),
                eventName = it.optString("eventName"),
                eventId = it.optString("eventId"),
                eventDate = it.optString("eventDate"),
                eventUrlSlug = it.optString("eventUrlSlug"),
                thumbUrl = it.optString("thumbUrl"),
                releaseCode = it.optString("releaseCode"),
                mediaType = it.optString("mediaType"),
                dateSubmitted = it.optString("dateSubmitted"),
                slot = it.optInt("slot"),
                isAnalogArchive = it.optBoolean("isAnalogArchive"),
                isCallForImage = it.optBoolean("isCallForImage"),
                isIstockSignature = it.optBoolean("isIstockSignature"),
                title = it.optString("title"),
                shortTitle = it.optString("shortTitle"),
                caption = it.optString("caption"),
                colorIndexes = it.optJSONArray("colorIndexes")?.let { jsonArray ->
                    List(jsonArray.length()) { jsonArray.getString(it) }
                },
                maxDimensions = it.optJSONObject("maxDimensions")?.run {
                    Dimensions(
                        width = optInt("width"),
                        height = optInt("height")
                    )
                },
                people = it.optString("people"),
                artist = it.optString("artist"),
                collectionCode = it.optString("collectionCode"),
                collectionName = it.optString("collectionName"),
                contributorDisplayName = it.optString("contributorDisplayName"),
                contributorMemberName = it.optString("contributorMemberName"),
                collapsedCount = it.optString("collapsedCount"),
                landingUrl = it.optString("landingUrl"),
                localizedCaption = it.optString("localizedCaption"),
                dateCreated = it.optString("dateCreated"),
                is360 = it.optBoolean("is360"),
                isFilm = it.optBoolean("isFilm"),
                isOffline = it.optBoolean("isOffline"),
                isExclusiveContent = it.optBoolean("isExclusiveContent"),
                isEmbeddable = it.optBoolean("isEmbeddable"),
                seoTitle = it.optString("seoTitle"),
                seoCaption = it.optString("seoCaption"),
                orientation = it.optString("orientation"),
                topKeywords = it.optString("topKeywords"),
                usageInfo = it.optString("usageInfo"),
                uploadDate = it.optString("uploadDate"),
                videoUploadDate = it.optString("videoUploadDate")

            )
        }
    }

    fun ServerResponse.asDomain(state: Boolean): PhotoData =
            PhotoData(
                photoId = id?.toInt() ?: 0,
                photoURL = thumbUrl ?: "",
                favorite = state,
                title = title ?: "",
                artist = artist ?: "",
                uploadDate = uploadDate ?: ""
            )

    fun List<FavoriteEntity>.asDomain(): List<PhotoData> =
        this.map {
            PhotoData(
                photoId = it.id,
                photoURL = it.photoUrl,
                favorite = it.favorite,
                title = it.title,
                artist = it.artist,
                uploadDate = it.uploadTime,
            )
        }

    fun PhotoData.asEntity(): FavoriteEntity =
        FavoriteEntity(
            id = photoId,
            photoUrl = photoURL,
            favorite = favorite,
            title = title,
            artist = artist,
            uploadTime = uploadDate,
        )
}