package com.rsupport.mobile1.test.activity.data.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import javax.inject.Inject

class GettyService @Inject constructor() {
    suspend fun getGettyItem(url : String): Element {
        return withContext(Dispatchers.IO) {
            Jsoup.connect(url).get()
        }
    }
}