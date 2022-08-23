package com.rsupport.mobile1.test.network

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
@ExperimentalCoroutinesApi
class NetworkServiceTestSet {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var gettyImagesService: GettyImagesService

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun getPhotosCollaborationAsyncTest() = runTest {
        val gettyImages = gettyImagesService.getPhotosCollaborationAsync().await()
        gettyImages.gallery.assets.forEach { asset ->
            assertTrue(asset.licenseType.isNotEmpty())
            assertTrue(asset.family.isNotEmpty())
            assertTrue(asset.shortTitle.isNotEmpty())
            assertTrue(asset.artist.isNotEmpty())
            assertTrue(asset.collectionName.isNotEmpty())
            assertTrue(asset.id.isNotEmpty())
            assertTrue(asset.thumbUrl.isNotEmpty())
        }
    }

}