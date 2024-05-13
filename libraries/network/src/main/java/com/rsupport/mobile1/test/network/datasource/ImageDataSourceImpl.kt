package com.rsupport.mobile1.test.network.datasource

import android.util.Log
import com.rsupport.mobile1.test.network_api.datasource.ImageDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import javax.inject.Inject

internal class ImageDataSourceImpl @Inject constructor(
) : ImageDataSource {
    override suspend fun getImage(page: Int): List<String> = withContext(Dispatchers.IO) {
        try {
            val url = "https://www.gettyimages.com/photos/collaboration?assettype=image&sort=mostpopular&phrase=collaboration&license=rf,rm&page=$page"
            val doc = Jsoup.connect(url).get()
            val imageUrls = doc
                .select("picture")
                .select("img[src]")
                .map { element -> element.attr("src") }
            Log.e("test", imageUrls.toString())
            imageUrls
        } catch (e: Exception) {
            emptyList<String>()
        }
    }
}