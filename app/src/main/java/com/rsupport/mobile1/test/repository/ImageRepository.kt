package com.rsupport.mobile1.test.repository

import com.rsupport.mobile1.test.model.Image
import com.rsupport.mobile1.test.network.ImageService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ImageRepository
@Inject
constructor(private val imageService: ImageService) {
    private val baseUrl = "https://www.gettyimages.com"

    suspend fun getImages(page: Int): Flow<List<Image>> = flow {
        // 웹 크롤링 로직
        val url = "$baseUrl/photos/collaboration?assettype=image&sort=mostpopular&phrase=collaboration&license=rf,rm&page=$page"
        val doc = imageService.getImages(url)
        val imageUrls = doc?.select("img[src]")
            ?.map { element -> element.attr("src") }
            // 정규표현식으로 이미지 url 패턴 체크를 해서 리스트 저장
            ?.filter { it.matches(Regex("https?://(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}(?:/[^/?]+)+\\.(?:jpg|jpeg).*")) }
            ?: emptyList() // null 일 경우 빈 리스트 반환
        emit(imageUrls.map { Image(it) })
    }
}