package com.blue.domain.usecase.favorite

import com.blue.domain.model.PhotoData
import com.blue.domain.repo.FavoriteRepo
import kotlinx.coroutines.flow.Flow

class GetFavoriteIdUseCase(
    private val favoriteRepo: FavoriteRepo
) {
    /**
     * 내부 저장소를 통해 "좋아요"를 누른 Photo ID List를 제공하는 함수입니다.
     * 네트워크 통신을 통해 제공받은 Photo List와 병합하여 "좋아요"유무를 파악합니다.
     *
     * @return List<Int>를 Flow로 반환합니다.
     */
    operator fun invoke(): Flow<List<Int>> =
        favoriteRepo.getFavoriteIdRepo()
}