package com.blue.data.remote.datasource

import com.blue.data.remote.model.ServerResponse
import com.blue.data.mapper.Mapper.asResponse
import org.json.JSONObject
import org.jsoup.Jsoup
import retrofit2.Retrofit
import javax.inject.Inject

class PhotoDataSourceImpl @Inject constructor(
    private val retrofit: Retrofit
) {
    suspend fun getPhotoDataSource(): List<ServerResponse> {
        val response = retrofit.create(PhotoDataSource::class.java).getPhotoDataSource()
        val doc = Jsoup.parse(response)
        val scriptContent = doc.select("script[data-component='Search']").firstOrNull()?.html().orEmpty()
        val assetsJsonArray = JSONObject(scriptContent)
            .optJSONObject("search")
            ?.optJSONObject("gallery")
            ?.optJSONArray("assets")
            ?.let { assetsArray ->
                List(assetsArray.length()) { assetsArray.getJSONObject(it) }
            } ?: emptyList()
        return assetsJsonArray.asResponse()
    }
}

