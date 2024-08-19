package com.rsupport.mobile1.test.data

import com.rsupport.mobile1.test.data.domain.Photo
import com.rsupport.mobile1.test.data.domain.asDomainModel
import com.rsupport.mobile1.test.data.dto.PhotoDto
import com.rsupport.mobile1.test.data.remote.PhotoRemoteDataSource
import io.reactivex.rxjava3.core.Single
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import javax.inject.Inject


class PhotoRepositoryImpl @Inject constructor(private val photoRemoteDataSource: PhotoRemoteDataSource): PhotoRepository {

    companion object {
        private const val PHOTO_DIV_SELECTOR = "div.vItTTzk8rQvUIXjdVfi4"
    }

    override fun fetchCollaborationPhoto(): Single<List<Photo>> {
        return photoRemoteDataSource.fetchCollaborationPhotoHtml().map { html ->
            parseHtml(html).map { it.asDomainModel() }
        }
    }

    private fun parseHtml(html: String): List<PhotoDto> {
        val doc = Jsoup.parse(html)
        val divTagElements = doc.select(PHOTO_DIV_SELECTOR)

        return divTagElements.map {
            val name = it.getMetaContent("name")
            val description = it.getMetaContent("description")
            val thumbnailUrl = it.getMetaContent("thumbnailUrl")

            PhotoDto(name, description, thumbnailUrl)
        }
    }

    private fun Element.getMetaContent(itemProp: String): String {
        return this.select("meta[itemprop=$itemProp]").attr("content")
    }
}

