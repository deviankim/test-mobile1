package com.kimwijin.data.source.remote

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

/**
 * WebService 인터페이스를 구현하여 Jsoup을 사용해 웹 페이지를 가져오는 클래스 입니다.
 * 지정된 URL에서 웹 페이지를 가져와 Jsoup Document로 반환 합니다.
 *
 *
 * @author (김위진)
 * @since (2024-05-21)
 */
class JsoupWebServiceImpl : WebService {

    override suspend fun fetchWebPage(url: String, page: String): Document =
        Jsoup.connect("$url&page=$page").get()
}