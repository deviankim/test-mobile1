package com.blue.domain.usecase.favorite

import com.blue.domain.repo.FavoriteRepo

class DeleteFavoriteUseCase(
    private val favoriteRepo: FavoriteRepo
) {
    /**
     * Photo Id를 통해 내부 저장소에 존재하는 Photo 를 삭제하는 함수입니다.
     *
     * @param "좋아요"를 누른 Photo ID를 받습니다.
     */
    suspend operator fun invoke(photoId: Int){
        favoriteRepo.deleteFavoriteRepo(photoId)
    }
}