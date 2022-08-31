package com.rsupport.mobile1.test.data

import android.util.Log
import com.rsupport.mobile1.test.util.Constants
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.jsoup.Jsoup
import java.io.IOException

@ExperimentalCoroutinesApi
class RemoteGettyImage() : DataSource {
    override fun getGettyImage(page: Int): Flow<MutableList<GettyImage>?> = flow {
        val url = Constants.GETTY_IMAGE_URL + "?page=$page"
        val list =  getGettyImagesFromUrl(url)
        emit(list)
    }.flowOn(Dispatchers.IO)

    private fun getGettyImagesFromUrl(gettyImageUrl: String): MutableList<GettyImage>? {
        try {
            val document = Jsoup.connect(gettyImageUrl).get()
            val gettyImages: MutableList<GettyImage> = mutableListOf()

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
            Log.e("DEBUG", "크롤링 에러 : $e")
            return null
        }
    }

}