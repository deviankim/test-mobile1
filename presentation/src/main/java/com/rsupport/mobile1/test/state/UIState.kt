package com.rsupport.mobile1.test.state

import com.blue.domain.model.PhotoData

sealed interface UIState {
    data object Loading: UIState

    data class Error(
        val mainMassage: String,
        val subMassage: String,
    ) : UIState

    data class Success(
        val data: List<PhotoData>
    ) : UIState
}