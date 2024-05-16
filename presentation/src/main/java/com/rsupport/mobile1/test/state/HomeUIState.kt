package com.rsupport.mobile1.test.state

import com.blue.domain.model.PhotoData

sealed interface HomeUIState {
    val data: List<PhotoData>
    data class Loading(
        override val data: List<PhotoData> = emptyList()
    ): HomeUIState

    data class Error(
        val mainMassage: String,
        val subMassage: String,
        override val data: List<PhotoData>  = emptyList(),
    ) : HomeUIState

    data class Success(
        override val data: List<PhotoData>,
    ) : HomeUIState
}