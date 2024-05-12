package com.rsupport.mobile1.test.feature_crawling

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun CrawlingScreen(navHostController: NavHostController) {
    Column {
        Text("crawling screen!")
    }

}

/*
override fun getImageUrlList(page: Int): Flow<List<String>> = flow {
    val url = "https://www.gettyimages.com/photos/collaboration?assettype=image&sort=mostpopular&phrase=collaboration&license=rf,rm&page=$page"
    val doc = Jsoup.connect(url).get()
    val imageUrls = doc
        .select("picture")
        .select("img[src]")
        .map { element -> element.attr("src") }
    emit(imageUrls)
}.flowOn(Dispatchers.IO)
 */