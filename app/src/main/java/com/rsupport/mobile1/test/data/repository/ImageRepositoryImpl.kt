package com.rsupport.mobile1.test.data.repository

import android.util.Log
import com.rsupport.mobile1.test.domain.model.Image
import com.rsupport.mobile1.test.domain.repository.ImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor() : ImageRepository {

    override suspend fun getImageList(page: Int): Result<List<Image>> {
        return try {
            withContext(Dispatchers.IO) {
                val document = Jsoup.connect("https://www.gettyimages.com/photos/collaboration?page=${page}").get()
                val elements = document.select("[class~=^MosaicAsset-module__thumb]")

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
