package com.blue.domain.usecase.favorite

import com.blue.domain.model.PhotoData
import com.blue.domain.repo.FavoriteRepo
import kotlinx.coroutines.flow.Flow

class GetFavoriteUseCase(
    private val favoriteRepo: FavoriteRepo
) {
    /**
     * 내부 저장소를 통해 "좋아요"를 누른 Photo List를 제공하는 함수입니다.
     *
     * @return List<PhotoData>를 Flow로 반환합니다.
     */
    operator fun invoke(): Flow<List<PhotoData>> =
        favoriteRepo.getFavoriteRepo()
}