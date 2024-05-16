package com.blue.domain.usecase.photo

import com.blue.domain.model.PhotoData
import com.blue.domain.repo.FavoriteRepo
import com.blue.domain.repo.PhotoRepo
import com.blue.domain.state.ResourceState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class GetPhotoUseCase(
    private val photoRepo: PhotoRepo
) {
    operator fun invoke(): Flow<ResourceState<List<PhotoData>>> = flow {
        emit(ResourceState.Loading())
        emit(photoRepo.getPhotoData())
    }
}
