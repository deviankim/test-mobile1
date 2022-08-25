package com.rsupport.mobile1.test.view.gallery

import com.rsupport.mobile1.test.model.dto.GettyImages
import com.rsupport.mobile1.test.repository.GettyImagesRepository
import com.rsupport.mobile1.test.repository.ResourceRepository
import com.rsupport.mobile1.test.rule.MainDispatcherRule
import com.rsupport.mobile1.test.util.getJsonString
import com.rsupport.mobile1.test.util.toInstance
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GalleryViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var resourceRepository: ResourceRepository
    private lateinit var gettyImagesRepository: GettyImagesRepository
    private lateinit var viewModel: GalleryViewModel

    @Before
    fun setup() {
        resourceRepository = mockk()
        gettyImagesRepository = mockk()
        viewModel = GalleryViewModel(resourceRepository, gettyImagesRepository)
    }

    @Test
    fun `when getPhotosCollaboration then return galleryUiModel`() = runTest {
        every { resourceRepository.getDimensionResource(any()) } returns 100f
        val gettyImages: GettyImages = getJsonString(this, "json/gettyImages.json").toInstance()
        coEvery { gettyImagesRepository.getPhotosCollaboration() } returns gettyImages

        viewModel.getPhotosCollaboration()
        advanceUntilIdle()

        val items = viewModel.galleryUiModelFlow.first().items
        assertNotNull(items)

        items!!.first().let {
            assertEquals(" Orbon Alija", it.artist)
            assertEquals("1180187740", it.id)
            assertEquals("E+", it.collectionName)
            assertEquals("Creative", it.family)
            assertEquals("RF", it.licenseType)
            assertEquals("Aerial view of crowd connected by lines", it.shortTitle)
            assertEquals("https://media.gettyimages.com/photos/aerial-view-of-crowd-connected-by-lines-picture-id1180187740?k=20&m=1180187740&s=612x612&w=0&h=s5vqexDzwt8irE-SC1wLWUUDompG9ehBDPrGTe5SsSU=", it.thumbnailInfo.thumbUrl)
            assertEquals(100, it.thumbnailInfo.thumbnailSize)
        }
    }
}