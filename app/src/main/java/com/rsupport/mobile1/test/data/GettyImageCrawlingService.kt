package com.rsupport.mobile1.test.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import javax.inject.Inject

class GettyImageCrawlingService @Inject constructor() {
    suspend fun getThumbnailUrlList(query: String = DEFAULT_QUERY): Result<List<String>> {
        return withContext(Dispatchers.IO) {
            kotlin.runCatching {
                val searchUrl = "$BASE_URL$query"
                Jsoup.connect(searchUrl).get()
                    .select(THUMBNAIL_TAG_CLASS_ID)
                    .map { it.attr(THUMBNAIL_URL_ATTRIBUTE) }
            }
        }
    }

    companion object {
        private const val BASE_URL = "https://www.gettyimages.com/photos/"
        private const val DEFAULT_QUERY = "collaboration"
        private const val THUMBNAIL_TAG_CLASS_ID = "img.MosaicAsset-module__thumb___yvFP5"
        private const val THUMBNAIL_URL_ATTRIBUTE = "src"
    }
}