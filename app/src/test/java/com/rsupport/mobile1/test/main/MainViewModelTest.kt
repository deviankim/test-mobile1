package com.rsupport.mobile1.test.main

import com.rsupport.mobile1.test.model.dto.GettyImages
import com.rsupport.mobile1.test.network.GettyImagesService
import com.rsupport.mobile1.test.rule.MainDispatcherRule
import com.rsupport.mobile1.test.util.getJsonString
import com.rsupport.mobile1.test.util.toInstance
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var gettyImagesService: GettyImagesService
    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        gettyImagesService = mockk()
        viewModel = MainViewModel(gettyImagesService)
    }

    @Test
    fun `when getPhotosCollaboration then return galleryModel list`() = runTest {
        val gettyImages: GettyImages = getJsonString(this, "json/gettyImages.json").toInstance()
        coEvery { gettyImagesService.getPhotosCollaborationAsync() } returns async { gettyImages }

        launch {
            viewModel.getPhotosCollaboration()
        }
        advanceUntilIdle()

        viewModel.galleryModelFlow.first().first().let {
            assertEquals(" Orbon Alija", it.artist)
            assertEquals("1180187740", it.id)
            assertEquals("E+", it.collectionName)
            assertEquals("creative", it.family)
            assertEquals("RF", it.licenseType)
            assertEquals("Aerial view of crowd connected by lines", it.shortTitle)
            assertEquals("https://media.gettyimages.com/photos/aerial-view-of-crowd-connected-by-lines-picture-id1180187740?k=20&m=1180187740&s=612x612&w=0&h=s5vqexDzwt8irE-SC1wLWUUDompG9ehBDPrGTe5SsSU=", it.thumbUrl)
        }
    }
}