package com.rsupport.mobile1.test.activity.data.network

import com.rsupport.mobile1.test.activity.data.model.ImageInformation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.HttpStatusException
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import javax.inject.Inject

private const val BASE_URL =
    "https://www.gettyimages.com/photos/collaboration?assettype=image&page="
private const val URL_SUFFIX = "&phrase=collaboration&sort=mostpopular&license=rf,rm"
private const val GALLERY_MOSAIC_ASSET_SELECTOR = "div[data-testid=galleryMosaicAsset]"
private const val MOSAIC_ASSET_TITLE_SELECTOR = "a[data-testid=mosaicAssetTitle]"

class WebScrapper @Inject constructor() {

    suspend fun getImageInformation(pageId: Int): Result<List<ImageInformation>> =
        withContext(Dispatchers.IO) {
            val itemList = mutableListOf<ImageInformation>()

            try {
                val doc =
                    Jsoup.connect("$BASE_URL$pageId$URL_SUFFIX")
                        .get()
                val elements: Elements = doc.select(GALLERY_MOSAIC_ASSET_SELECTOR)

                for (element in elements) {
                    val title = element.select(MOSAIC_ASSET_TITLE_SELECTOR).text()
                    val imageUrl = element.select("img").attr("src")
                    itemList.add(ImageInformation(title, imageUrl))
                }
                Result.success(itemList.toList())
            } catch (e: HttpStatusException) {
                //잘못된 page id를 요청하는 경우 HTTP 404 오류 발생 == 더 이상 자료 없음
                Result.success(emptyList())
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
}