package com.example.data.jsoup

import com.example.data.model.GettyImageDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup

class GettyImageJsoupParser {

    suspend fun getGettyImages(page: Int): Result<List<GettyImageDto>> = withContext(Dispatchers.IO) {
        runCatching {
            val doc = Jsoup.connect(GETTY_IMAGES_BASE_URL + page).get()
            doc
                .select("picture")
                .select("img[src]")
                .map {
                    GettyImageDto(it.attr("src"))
                }
                .distinctBy { it.imageUrl }
        }
    }

    companion object {
        private const val GETTY_IMAGES_BASE_URL =
            "https://www.gettyimages.com/photos/collaboration?assettype=image&sort=mostpopular&phrase=collaboration&license=rf,rm&page="
    }
}