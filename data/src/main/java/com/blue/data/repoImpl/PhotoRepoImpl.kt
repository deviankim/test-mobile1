package com.blue.data.repoImpl

import com.blue.data.mapper.Mapper.asDomain
import com.blue.data.remote.datasource.PhotoDataSourceImpl
import com.blue.data.remote.state.ResponseState
import com.blue.domain.model.PhotoData
import com.blue.domain.repo.PhotoRepo
import com.blue.domain.state.ResourceState
import javax.inject.Inject

class PhotoRepoImpl @Inject constructor(
    private val photoDataSource: PhotoDataSourceImpl,
): PhotoRepo {
    override suspend fun getPhotoData(): ResourceState<List<PhotoData>> {
        return when(val result = photoDataSource.getPhotoDataSource()){
            is ResponseState.Success -> ResourceState.Success(result.data.map { it.asDomain() })
            is ResponseState.Error -> ResourceState.Error(result.failure)
        }
    }
}