package com.rsupport.mobile1.test.network

import com.rsupport.mobile1.test.network.baseurl.BaseUrlFactoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Test

@ExperimentalCoroutinesApi
class NetworkServiceTestSet {

    private val gettyImagesService: GettyImagesService = NetworkService(BaseUrlFactoryImpl()).gettyImagesService

    @Test
    fun real_gettyImagesService_getPhotosCollaborationAsync_test() = runTest {
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