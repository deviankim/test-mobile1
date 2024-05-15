package com.blue.domain.usecase.favorite

import android.provider.ContactsContract.Contacts.Photo
import com.blue.domain.model.PhotoData
import com.blue.domain.repo.FavoriteRepo

class AddFavoriteUseCase(
    private val favoriteRepo: FavoriteRepo
) {
    suspend operator fun invoke(data: PhotoData){
        favoriteRepo.addFavoriteRepo(data)
    }
}