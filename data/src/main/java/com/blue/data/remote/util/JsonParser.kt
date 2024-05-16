package com.blue.data.remote.util

import org.json.JSONObject
import org.jsoup.Jsoup

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