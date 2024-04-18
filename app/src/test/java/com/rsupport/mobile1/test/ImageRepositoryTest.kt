package com.rsupport.mobile1.test

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.rsupport.mobile1.test.model.Image
import com.rsupport.mobile1.test.network.ImageService
import com.rsupport.mobile1.test.repository.ImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.jsoup.nodes.Document
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ImageRepositoryTest {

    private lateinit var imageService: ImageService
    private lateinit var imageRepository: ImageRepository

    @Before
    fun setup() {
        imageService = mock()
        imageRepository = ImageRepository(imageService)
    }

    @Test
    fun `test getImages with empty document`() {
        // Given
        val page = 1
        val url = "https://www.gettyimages.com/photos/collaboration?assettype=image&sort=mostpopular&phrase=collaboration&license=rf,rm&page=$page"
        val emptyDoc: Document? = null

        runBlocking {
            whenever(imageService.getImages(url)).thenReturn(emptyDoc)

            // When
            val resultFlow: Flow<List<Image>> = imageRepository.getImages(page)

            // Then
            val result = resultFlow.toList().flatten()
            assertEquals(0, result.size)
        }
    }
}
