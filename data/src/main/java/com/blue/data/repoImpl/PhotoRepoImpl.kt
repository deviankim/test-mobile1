package com.blue.data.repoImpl

import com.blue.data.remote.datasource.PhotoDataSource
import com.blue.domain.repo.PhotoRepo
import javax.inject.Inject

class PhotoRepoImpl @Inject constructor(
    private val photoDataSource: PhotoDataSource
): PhotoRepo {
    override suspend fun getPhotoData() {
        photoDataSource.getPhotoDataSource()
    }
}