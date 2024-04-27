package com.rsupport.mobile1.test.activity.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.jsoup.Jsoup
import javax.inject.Inject

interface ImageRepository {
    fun getImageUrlList(page: Int): Flow<List<String>>
}


class ImageRepositoryImpl @Inject constructor(): ImageRepository {

    override fun getImageUrlList(page: Int): Flow<List<String>> = flow {
        val url = "https://www.gettyimages.com/photos/collaboration?assettype=image&sort=mostpopular&phrase=collaboration&license=rf,rm&page=$page"
        val doc = Jsoup.connect(url).get()
        val imageUrls = doc
            .select("picture")
            .select("img[src]")
            .map { element -> element.attr("src") }
        emit(imageUrls)
    }.flowOn(Dispatchers.IO)
}