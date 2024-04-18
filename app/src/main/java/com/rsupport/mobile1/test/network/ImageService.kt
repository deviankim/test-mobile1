package com.rsupport.mobile1.test.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import javax.inject.Inject

class ImageService
@Inject
constructor() {
    suspend fun getImages(url: String): Element? {
        return withContext(Dispatchers.IO) {
            Jsoup.connect(url).get()
        }
    }
}