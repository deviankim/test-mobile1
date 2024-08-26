package com.example.data.remote.source

import com.example.data.jsoup.GettyImageJsoupParser
import com.example.data.model.GettyImageDto
import javax.inject.Inject

class GettyImagesRemoteDataSourceImpl @Inject constructor(
    private val gettyImageJsoupParser: GettyImageJsoupParser
) : GettyImagesRemoteDataSource {

    override suspend fun getGettyImages(page: Int): Result<List<GettyImageDto>> = gettyImageJsoupParser.getGettyImages(page)
}
