package com.blue.domain.usecase.favorite

import com.blue.domain.repo.FavoriteRepo

class DeleteFavoriteUseCase(
    private val favoriteRepo: FavoriteRepo
) {
    suspend operator fun invoke(photoId: Int){
        favoriteRepo.deleteFavoriteRepo(photoId)
    }
}