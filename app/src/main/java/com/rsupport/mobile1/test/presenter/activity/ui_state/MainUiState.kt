package com.rsupport.mobile1.test.presenter.activity.ui_state

sealed class MainUiState {

    object None : MainUiState()

    data class Success(
        val uiDataList: List<MainUiData>,
    ) : MainUiState()

    data class Fail(
        val e: Throwable,
    ) : MainUiState()

    object Loading : MainUiState()
}