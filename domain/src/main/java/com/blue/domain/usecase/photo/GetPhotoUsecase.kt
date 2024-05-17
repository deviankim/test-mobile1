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
    /**
     * 네트워크 통신을 통해 Photo List를 제공하는 함수입니다.
     *
     * @return Loading과 List<PhotoData>를 Flow로 반환합니다.
     */
    operator fun invoke(): Flow<ResourceState<List<PhotoData>>> = flow {
        emit(ResourceState.Loading())
        emit(photoRepo.getPhotoData())
    }
}
