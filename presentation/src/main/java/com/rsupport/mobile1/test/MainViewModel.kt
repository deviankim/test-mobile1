package com.rsupport.mobile1.test

import androidx.lifecycle.ViewModel
import com.blue.domain.usecase.favorite.AddFavoriteUseCase
import com.blue.domain.usecase.favorite.DeleteFavoriteUseCase
import com.blue.domain.usecase.photo.GetPhotoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPhotoUseCase: GetPhotoUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase
): ViewModel() {
    init {
        getPhotoUseCase
    }
}