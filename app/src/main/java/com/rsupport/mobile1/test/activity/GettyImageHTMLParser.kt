package com.rsupport.mobile1.test.activity

import org.jsoup.Jsoup

class GettyImageHTMLParser {

    fun getHTMLData(): String {
        val baseUrl = "https://www.gettyimages.com/photos"
        val keyword = "collaboration"

        val document = Jsoup.connect("${baseUrl}/${keyword}").get()
        val jsonElement = document.selectFirst("script[type=application/json][data-component=Search]")
        return jsonElement.html()
    }
}