package com.blue.domain.usecase.photo

import com.blue.domain.model.PhotoData
import com.blue.domain.repo.FavoriteRepo
import com.blue.domain.repo.PhotoRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class GetPhotoUseCase(
    private val photoRepo: PhotoRepo
) {
    operator fun invoke(): Flow<List<PhotoData>> =
        photoRepo.getPhotoData()
}
