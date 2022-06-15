package com.rsupport.mobile1.test

import com.rsupport.mobile1.test.activity.data.GettyImageCrawlingService
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GettyCrawlingUnitTest {
    private val service = GettyImageCrawlingService()

    private fun printAllItems(itemBank: List<String>) {
        itemBank.withIndex().forEach {
            println("${it.index}: ${it.value}")
        }
    }

    private suspend fun logThumbnailUrls(query: String) {
        println("Search [${query}] Thumbnail URL from GettyImage")
        service.getThumbnailUrlList(query).also { result ->
            result
                .onSuccess { printAllItems(it) }
                .onFailure { println("fail to load thumbnail urls .. 😰  $it") }
        }
    }

    @Test
    fun logAndroidThumbnails() = runTest {
        logThumbnailUrls("Android")
    }

    @Test
    fun logCatThumbnails() = runTest {
        logThumbnailUrls("Cat")
    }

    @Test
    fun logDeveloperThumbnails() = runTest {
        logThumbnailUrls("Developer")
    }

    @Test
    fun logCollaborationThumbnails() = runTest {
        logThumbnailUrls("collaboration")
    }
}