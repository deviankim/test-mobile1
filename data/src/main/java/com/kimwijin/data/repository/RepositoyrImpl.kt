package com.kimwijin.data.repository

import com.kimwijin.common.log.TestAppLogger
import com.kimwijin.data.source.remote.WebService
import com.kimwijin.data.util.PAGE_URL
import com.kimwijin.domain.base.Result
import com.kimwijin.domain.model.ImageInfo
import com.kimwijin.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import javax.inject.Inject

/**
 * Repository 의 구현체 클래스 입니다.
 * 이 클래스는 네트워크에서 데이터를 가져오고, 앱에서 사용할 수 있도록 데이터를 처리하는 책임을 지고 있습니다.
 * ApiService의 API 응답을 도메인 모델로 매핑하고, 필요한 비즈니스 로직을 적용합니다.
 *
 * Repository 인터페이스를 구현하며, 의존성 주입을 통해 ApiService와 BookmarkDao에 연결됩니다.
 *
 * @author (김위진)
 * @since (2024-05-20)
 */
class RepositoryImpl @Inject constructor(
    private val webService: WebService
) : Repository {
    override suspend fun getImages(page: String): Flow<Result<List<ImageInfo>>> = flow {
        try {
            val doc: Document = webService.fetchWebPage(PAGE_URL, page)
            val images: Elements = doc.select("img")
            val imageInfoList = images.mapNotNull {
                if (it.attr("class").isNullOrEmpty()) {
                    null
                } else {
                    val src = it.attr("src")
                    val title = it.attr("alt").ifEmpty { "No title" }
                    val width = it.attr("width").ifEmpty { "" }
                    val height = it.attr("height").ifEmpty { "" }
                    ImageInfo(src, title, width, height)
                }
            }

            emit(Result.Success(imageInfoList))

        } catch (e: Exception) {
            TestAppLogger.e("RepositoryImpl ERROR = $e")
            emit(Result.Exception(e))
        }
    }
}