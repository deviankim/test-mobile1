package com.blue.domain.usecase.favorite

import com.blue.domain.repo.FavoriteRepo

class AddFavoriteUseCase(
    private val favoriteRepo: FavoriteRepo
) {
    suspend operator fun invoke(){
        favoriteRepo.addFavoriteRepo(1)
    }
}