package com.rsupport.mobile1.test.activity.data.remote

import org.jsoup.Jsoup
import javax.inject.Inject

class GettyImageHTMLParser @Inject constructor() {

    fun getHTMLData(): String {
        val baseUrl = "https://www.gettyimages.com/photos"
        val keyword = "collaboration"

        val document = Jsoup.connect("${baseUrl}/${keyword}").get()
        val jsonElement = document.selectFirst("script[type=application/json][data-component=Search]")
        return jsonElement.html()
    }
}