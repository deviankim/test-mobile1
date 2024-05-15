package com.blue.domain.usecase.photo

import com.blue.domain.model.PhotoData
import com.blue.domain.repo.FavoriteRepo
import com.blue.domain.repo.PhotoRepo
import kotlinx.coroutines.flow.Flow

class GetPhotoUseCase(
    private val photoRepo: PhotoRepo
) {
    suspend operator fun invoke(): Flow<List<PhotoData>> =
        photoRepo.getPhotoData()
}