package com.rsupport.mobile1.test.data.datasource.remote

import org.jsoup.Jsoup
import org.jsoup.select.Elements
import javax.inject.Inject

class GettyImageHTMLParser @Inject constructor() {

    fun getPhotosResult(
        keyword: String,
        selector: String,
        page: Int
    ): Elements? {
        val document =
            Jsoup.connect("https://www.gettyimages.com/photos/${keyword}?page=${page}").get()

        return document.select(selector)
    }
}