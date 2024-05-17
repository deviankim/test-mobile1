package com.blue.domain.usecase.favorite

import android.provider.ContactsContract.Contacts.Photo
import com.blue.domain.model.PhotoData
import com.blue.domain.repo.FavoriteRepo

class AddFavoriteUseCase(
    private val favoriteRepo: FavoriteRepo
) {
    /**
     * "좋아요"를 누른 Photo Data를 내부 저장소에 저장하는 함수입니다.
     *
     * @param "좋아요"를 누른 Photo를 받습니다.
     */
    suspend operator fun invoke(data: PhotoData){
        favoriteRepo.addFavoriteRepo(data)
    }
}