package com.rsupport.mobile1.test.domain.data

import com.rsupport.mobile1.test.data.database.entities.GettyEntity

data class GettyDomainData(
    val author: String?,
    val date: String?,
    val thumbnailUrl: String?
)

internal fun GettyDomainData.toEntity(): GettyEntity {
    return GettyEntity(
        id = 0,
        author = author,
        date = date,
        thumbnailUrl = thumbnailUrl
    )
}