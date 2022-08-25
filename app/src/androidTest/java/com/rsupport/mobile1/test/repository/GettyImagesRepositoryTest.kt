package com.rsupport.mobile1.test.repository

import com.rsupport.mobile1.test.model.dto.GettyImages
import com.rsupport.mobile1.test.network.GettyImagesService
import com.rsupport.mobile1.test.rule.MockServerRule
import com.rsupport.mobile1.test.util.getJsonString
import com.rsupport.mobile1.test.util.toInstance
import com.rsupport.mobile1.test.util.toMockResponse
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltAndroidTest
class GettyImagesRepositoryTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val mockServerRule = MockServerRule()

    @Inject
    lateinit var mockGettyImagesService: GettyImagesService

    private lateinit var gettyImagesRepository: GettyImagesRepository
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        hiltRule.inject()
        gettyImagesRepository = GettyImagesRepository(mockGettyImagesService, testDispatcher)
    }

    @Test
    fun gettyImagesService_getPhotosCollaborationAsync_test() = runTest(testDispatcher) {
        val jsonStr = getJsonString("json/gettyImages.json")
        val gettyImages: GettyImages = jsonStr.toInstance()
        mockServerRule.server.enqueue(gettyImages.toMockResponse())

        val actual = gettyImagesRepository.getPhotosCollaboration()
        actual.gallery.assets.first().let {
            Assert.assertEquals(" Orbon Alija", it.artist)
            Assert.assertEquals("1180187740", it.id)
            Assert.assertEquals("E+", it.collectionName)
            Assert.assertEquals("creative", it.family)
            Assert.assertEquals("RF", it.licenseType)
            Assert.assertEquals("Aerial view of crowd connected by lines", it.shortTitle)
            Assert.assertEquals("https://media.gettyimages.com/photos/aerial-view-of-crowd-connected-by-lines-picture-id1180187740?k=20&m=1180187740&s=612x612&w=0&h=s5vqexDzwt8irE-SC1wLWUUDompG9ehBDPrGTe5SsSU=", it.thumbUrl)
        }
    }


}