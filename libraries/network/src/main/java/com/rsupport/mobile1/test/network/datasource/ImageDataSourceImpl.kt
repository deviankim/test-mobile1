package com.rsupport.mobile1.test.network.datasource

import com.rsupport.mobile1.test.network_api.datasource.ImageDataSource
import com.rsupport.mobile1.test.network_api.entity.Image
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import javax.inject.Inject

internal class ImageDataSourceImpl @Inject constructor(
) : ImageDataSource {
    override suspend fun getImage(page: Int): List<Image> = withContext(Dispatchers.IO) {
        try {
            val url =
                "https://www.gettyimages.com/photos/collaboration?assettype=image&sort=mostpopular&phrase=collaboration&license=rf,rm&page=$page"
            val doc = Jsoup.connect(url).get()
            val imageUrls = doc
                .select("picture")
                .select("img[src]")
                .map { element -> Image(element.attr("src")) }
            imageUrls
        } catch (e: Exception) {
            emptyList<Image>()
        }
    }
}