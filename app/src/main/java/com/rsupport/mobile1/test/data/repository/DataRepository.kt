package com.rsupport.mobile1.test.data.repository

import android.util.Log
import com.rsupport.mobile1.test.data.model.Photo
import com.rsupport.mobile1.test.utils.Constants
import org.jsoup.Jsoup
import java.io.IOException

class DataRepository {
    fun crawlFromWeb(page: Int): List<Photo>? {
        try {
            val url = Constants.URL + "?page=$page"
            val document = Jsoup.connect(url).get()
            val photos: MutableList<Photo> = mutableListOf()

            val photoHTML = document.select(".site-width")
                .select("div").select("div")
                .select(".Gallery-module__rowContainer___qyf9K")
                .select(".Gallery-module__columnContainer___LqU0P")
                .select(".GalleryItems-module__searchContent___DbMmK")
                .select("div").select("article")

            photoHTML.forEach { item ->
                val image = item.select("a").select("figure")
                    .select("picture").select("img").attr("src")
                if (!image.isNullOrEmpty()) {
                    val photo = Photo(image)
                    photos.add(photo)
                }
            }
            return photos
        } catch (e: IOException) {
            Log.e("DEBUG", "crawling error : $e")
            return null
        }
    }
}