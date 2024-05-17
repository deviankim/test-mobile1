package com.blue.data.remote.util

import org.json.JSONObject
import org.jsoup.Jsoup

/**
 * HTML String 데이터를 파싱하는 함수입니다.
 *
 * @param 타겟 URL의 Html 데이터를 String 타입으로 전달받습니다.
 *
 * @return Jsoup 라이브러리를 통해 타겟 데이터를 파싱한 후 List<JSONObject>으로 반환합니다.
 */
fun String.jsonParser(): List<JSONObject> {
    val doc = Jsoup.parse(this)
    val scriptContent = doc.select("script[data-component='Search']").firstOrNull()?.html().orEmpty()
    return JSONObject(scriptContent)
        .optJSONObject("search")
        ?.optJSONObject("gallery")
        ?.optJSONArray("assets")
        ?.let { assetsArray ->
            List(assetsArray.length()) { assetsArray.getJSONObject(it) }
        } ?: emptyList()
}