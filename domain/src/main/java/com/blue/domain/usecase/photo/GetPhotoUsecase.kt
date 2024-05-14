package com.blue.domain.usecase.photo

import com.blue.domain.repo.FavoriteRepo
import com.blue.domain.repo.PhotoRepo

class GetPhotoUseCase(
    private val photoRepo: PhotoRepo
) {
    suspend operator fun invoke(){
        photoRepo.getPhotoData()
    }
}