package com.rsupport.mobile1.test

import com.rsupport.mobile1.test.network.ImageService
import kotlinx.coroutines.runBlocking
import org.jsoup.nodes.Document
import org.junit.Assert.assertNotNull
import org.junit.Test

class ImageServiceTest {

    private val imageService = ImageService()

    @Test
    fun `test getImages`() {
        // Given
        val url = "https://www.example.com"

        // When
        val document = runBlocking { imageService.getImages(url) }

        // Then
        assertNotNull(document)
    }
}