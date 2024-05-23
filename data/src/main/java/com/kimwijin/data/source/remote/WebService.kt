package com.kimwijin.data.source.remote

import org.jsoup.nodes.Document

/**
 * 웹 페이지를 가져오기 위한 인터페이스 입니다.
 * 이 인터페이스는 Jsoup을 사용하여 웹 페이지를 가져옵니다.
 *
 *
 * @author (김위진)
 * @since (2024-05-21)
 */
interface WebService {
    suspend fun fetchWebPage(url: String, page: String): Document
}