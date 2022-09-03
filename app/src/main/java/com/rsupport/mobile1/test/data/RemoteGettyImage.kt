package com.rsupport.mobile1.test.data

import android.util.Log
import com.rsupport.mobile1.test.util.Constants
import org.jsoup.Jsoup
import java.io.IOException
import javax.inject.Inject

class RemoteGettyImage @Inject constructor() : DataSource {
    private val gettyImages: MutableList<GettyImage> = mutableListOf()
    override suspend fun getGettyImage(page: Int): MutableList<GettyImage> {
        val url = Constants.GETTY_IMAGE_URL + "?page=$page"
        return getGettyImagesFromUrl(url)
    }

    private fun getGettyImagesFromUrl(gettyImageUrl: String): MutableList<GettyImage> {
        try {
            val document = Jsoup.connect(gettyImageUrl).get()
            val gettyImageHTML = document.select(".GalleryItems-module__searchContent___DbMmK")
                .select("div").select("article")
            gettyImageHTML.forEach { item ->
                val image = item.select("a").select("figure")
                    .select("picture").select("img").attr("src")
                if (!image.isNullOrEmpty()) {
                    val gettyImage = GettyImage(image)
                    gettyImages.add(gettyImage)
                }
            }
            return gettyImages
        } catch (e: IOException) {
            Log.e("DEBUG", "Error : $e")
            return gettyImages
        }
    }

}