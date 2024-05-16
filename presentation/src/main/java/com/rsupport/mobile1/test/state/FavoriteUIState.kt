package com.rsupport.mobile1.test.state

import com.blue.domain.model.PhotoData

sealed interface FavoriteUIState {
    data object Loading: FavoriteUIState

    data class Error(
        val mainMassage: String,
        val subMassage: String,
    ) : FavoriteUIState

    data class Success(
        val data: List<PhotoData>,
    ) : FavoriteUIState
}