package com.rsupport.mobile1.test.data

import com.rsupport.mobile1.test.data.domain.Photo
import com.rsupport.mobile1.test.data.domain.Response
import com.rsupport.mobile1.test.data.domain.asDomainModel
import com.rsupport.mobile1.test.data.dto.CollaborationPhotoDto
import com.rsupport.mobile1.test.data.dto.PhotoDto
import com.rsupport.mobile1.test.data.remote.PhotoRemoteDataSource
import io.reactivex.rxjava3.core.Single
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import javax.inject.Inject


class PhotoRepositoryImpl @Inject constructor(private val photoRemoteDataSource: PhotoRemoteDataSource): PhotoRepository {

    companion object {
        private const val PHOTO_DIV_SELECTOR = "div.vItTTzk8rQvUIXjdVfi4"
        private const val MAX_PAGE_SPAN_SELECTOR = "span.JO4Dw2C5EjCB3iovKUcw"
    }

    private val photoItems = mutableListOf<Photo>()

    override fun fetchCollaborationPhoto(page: Int): Single<Response<List<Photo>>> {
        if (page == 1) photoItems.clear()

        return photoRemoteDataSource.fetchCollaborationPhotoHtml(page).map { html ->
            val parseDto = parseHtml(html)
            val photoList = parseDto.photos.map { it.asDomainModel() }
            photoItems.addAll(photoList)
            Response(photoItems.toList(), page < parseDto.maxPage)
        }
    }

    private fun parseHtml(html: String): CollaborationPhotoDto {
        val doc = Jsoup.parse(html)
        val maxPage = doc.select(MAX_PAGE_SPAN_SELECTOR).text()
        val divTagElements = doc.select(PHOTO_DIV_SELECTOR)

        val photoList =  divTagElements.map {
            val name = it.getMetaContent("name")
            val description = it.getMetaContent("description")
            val thumbnailUrl = it.getMetaContent("thumbnailUrl")

            PhotoDto(name, description, thumbnailUrl)
        }

        return CollaborationPhotoDto(photoList, maxPage.toIntOrNull() ?: 1)
    }

    private fun Element.getMetaContent(itemProp: String): String {
        return this.select("meta[itemprop=$itemProp]").attr("content")
    }
}

