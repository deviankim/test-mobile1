package com.blue.domain.usecase.favorite

import com.blue.domain.model.PhotoData
import com.blue.domain.repo.FavoriteRepo
import kotlinx.coroutines.flow.Flow

class GetFavoriteIdUseCase(
    private val favoriteRepo: FavoriteRepo
) {
    operator fun invoke(): Flow<List<Int>> =
        favoriteRepo.getFavoriteIdRepo()
}