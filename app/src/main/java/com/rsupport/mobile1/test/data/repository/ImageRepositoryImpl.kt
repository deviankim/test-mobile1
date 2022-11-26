package com.rsupport.mobile1.test.data.repository

import com.rsupport.mobile1.test.data.datasource.remote.GettyImageHTMLParser
import com.rsupport.mobile1.test.domain.model.Image
import com.rsupport.mobile1.test.domain.repository.ImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val gettyImageHTMLParser: GettyImageHTMLParser
) : ImageRepository {

    override suspend fun getImageList(page: Int): Result<List<Image>> {
        return try {
            withContext(Dispatchers.IO) {
                val elements = gettyImageHTMLParser.getPhotosResult(
                    keyword = "collaboration",
                    selector = "[class~=^MosaicAsset-module__thumb]",
                    page = page
                )

                if(elements == null) {
                    Result.success(emptyList())
                } else {
                    val data = elements.map {
                        val imageURL = it.absUrl("src")
                        var id = imageURL.replace("https://media.gettyimages.com/id/", "")
                        id = id.substring(0, id.indexOf("/"))

                        Image(id, imageURL)
                    }
                    Result.success(data)
                }
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
