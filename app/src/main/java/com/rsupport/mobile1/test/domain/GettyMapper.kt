package com.rsupport.mobile1.test.domain

import com.rsupport.mobile1.test.data.database.entities.GettyEntity
import com.rsupport.mobile1.test.domain.data.GettyDomainData
import com.rsupport.mobile1.test.util.Mapper
import javax.inject.Inject

class GettyMapper @Inject constructor() : Mapper.DtoToDomain<GettyEntity, GettyDomainData> {
    override fun map(dto: GettyEntity): GettyDomainData {
        return dto.toDomain()
    }
}

internal fun GettyEntity.toDomain(): GettyDomainData {
    return GettyDomainData(
        author = author,
        date = date,
        thumbnailUrl = thumbnailUrl
    )
}