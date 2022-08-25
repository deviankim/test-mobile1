package com.rsupport.mobile1.test.repository

import com.rsupport.mobile1.test.model.dto.GettyImages
import com.rsupport.mobile1.test.network.GettyImagesService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GettyImagesRepository @Inject constructor(
    private val gettyImagesService: GettyImagesService,
    private val ioDispatcher: CoroutineDispatcher
) {

    suspend fun getPhotosCollaboration(): GettyImages = withContext(ioDispatcher) {
        gettyImagesService.getPhotosCollaborationAsync().await()
    }

}