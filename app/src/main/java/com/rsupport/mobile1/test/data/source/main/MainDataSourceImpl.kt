package com.rsupport.mobile1.test.data.source.main

import com.rsupport.mobile1.test.data.model.main.MainDTO
import com.rsupport.mobile1.test.data.model.main.MainResponse
import com.rsupport.mobile1.test.domain.model.HtmlParseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import javax.inject.Inject

class MainDataSourceImpl @Inject constructor() : MainDataSource {

    override fun getMainList(): Flow<HtmlParseResult<MainResponse>> = flow {
        try {
            val url = "https://www.gettyimages.com/photos/collaboration?assettype=image&sort=mostpopular&phrase=collaboration&license=rf,rm&page=1"
            val document = fetchDocumentFromUrl(url)
            val imageList = parseImagesFromDocument(document)

            emit(HtmlParseResult.Success(MainResponse(imageList)))
        } catch (e: Exception) {
            emit(HtmlParseResult.Error(e))
        }
    }.flowOn(Dispatchers.IO)

    private fun fetchDocumentFromUrl(url: String): Document {
        return Jsoup.connect(url).get()
    }

    private fun parseImagesFromDocument(document: Document): List<MainDTO> {
        return document.select("div[data-testid=galleryMosaicAsset]").map {
            val title = it.select("a[data-testid=mosaicAssetTitle]").text()
            val imageUrl = it.select("img").attr("src")
            MainDTO(imageUrl, title)
        }
    }
}