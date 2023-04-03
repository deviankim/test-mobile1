package com.rsupport.mobile1.test.activity.data.repository

import android.util.Log
import com.rsupport.mobile1.test.activity.data.remote.GettyImageHTMLParser
import com.rsupport.mobile1.test.activity.data.model.GettyImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject

class GettyImageRepository @Inject constructor(
    private val gettyImageHTMLParser: GettyImageHTMLParser
) {

    companion object {
        private const val TAG = "GettyImageRepository"
    }

    suspend fun fetchGettyImageList(): List<GettyImage> = withContext(Dispatchers.IO) {
        val jsonData = gettyImageHTMLParser.getHTMLData()
        val gettyImageList = arrayListOf<GettyImage>()

        try {
            val jsonObject = JSONObject(jsonData)
            val assetsArray = jsonObject.getJSONObject("search").getJSONObject("gallery").getJSONArray("assets")

            for (i in 0 until assetsArray.length()) {
                val id = assetsArray.getJSONObject(i).getString("id")
                val thumbUrl = assetsArray.getJSONObject(i).getString("thumbUrl")
                Log.d(TAG, "id: $id, thumbUrl: $thumbUrl")

                gettyImageList.add(GettyImage(id, thumbUrl))
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return@withContext gettyImageList
    }
}