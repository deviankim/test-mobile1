package com.rsupport.mobile1.test.activity.data

import android.util.Log
import com.rsupport.mobile1.test.activity.presenter.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.IOException

class GettyImageWebImpl: GettyImageWeb {

    private val url = "https://www.gettyimages.com/photos/collaboration"

    override suspend fun requestGettyImage(): List<GettyImage>? = withContext(Dispatchers.IO) {

        var gettyImageList: List<GettyImage>? = null

        try {
            val doc: Document = Jsoup.connect(url).get()
            val elements: Elements = doc.select(".GalleryItems-module__searchContent___DbMmK")
            val isEmpty = elements.isEmpty()

            Log.d(MainActivity.TAG, "isNull? : $isEmpty")
            Log.d(MainActivity.TAG, "size : ${elements.size}")

            val innerGettyImageList: ArrayList<GettyImage> = arrayListOf()

            if (!isEmpty) {

                elements[0].select("div .MosaicAsset-module__galleryMosaicAsset____wxTl").forEachIndexed { index, it ->

                    val assetId = it.attr("data-asset-id")
                    val authorContent = it.select("article.MosaicAsset-module__container___YN2eK").select("meta[itemprop=author]").attr("content")
                    val captionContent = it.select("article.MosaicAsset-module__container___YN2eK").select("meta[itemprop=caption]").attr("content")
                    val contentUrlContent = it.select("article.MosaicAsset-module__container___YN2eK").select("meta[itemprop=contentUrl]").attr("content")
                    val creditTextContent = it.select("article.MosaicAsset-module__container___YN2eK").select("meta[itemprop=creditText]").attr("content")
                    val descriptionContent = it.select("article.MosaicAsset-module__container___YN2eK").select("meta[itemprop=description]").attr("content")
                    val nameContent = it.select("article.MosaicAsset-module__container___YN2eK").select("meta[itemprop=name]").attr("content")
                    val thumbnailUrlContent = it.select("article.MosaicAsset-module__container___YN2eK").select("meta[itemprop=thumbnailUrl]").attr("content")
                    val uploadDateContent = it.select("article.MosaicAsset-module__container___YN2eK").select("meta[itemprop=uploadDate]").attr("content")

                    Log.d(MainActivity.TAG, "$index : ${it.tagName()}")
                    Log.d(MainActivity.TAG, "assetId : $assetId")
                    Log.d(MainActivity.TAG, "authorContent : $authorContent")
                    Log.d(MainActivity.TAG, "captionContent : $captionContent")
                    Log.d(MainActivity.TAG, "contentUrlContent : $contentUrlContent")
                    Log.d(MainActivity.TAG, "creditTextContent : $creditTextContent")
                    Log.d(MainActivity.TAG, "descriptionContent : $descriptionContent")
                    Log.d(MainActivity.TAG, "nameContent : $nameContent")
                    Log.d(MainActivity.TAG, "thumbnailUrlContent : $thumbnailUrlContent")
                    Log.d(MainActivity.TAG, "uploadDateContent : $uploadDateContent")

                    innerGettyImageList.add(
                        GettyImage(
                            assetId = assetId,
                            author = authorContent,
                            caption = captionContent,
                            contentUrl = contentUrlContent,
                            creditText = creditTextContent,
                            description = descriptionContent,
                            name = nameContent,
                            thumbnailUrl = thumbnailUrlContent,
                            uploadDate = uploadDateContent,
                        )
                    )
                }
                gettyImageList = innerGettyImageList
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        gettyImageList
    }

}