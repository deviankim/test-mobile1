package com.rsupport.mobile1.test.presenter.activity

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rsupport.mobile1.test.domain.cache.GetCacheGettyInfoUseCase
import com.rsupport.mobile1.test.presenter.activity.ui_state.MainUiState
import com.rsupport.mobile1.test.util.Constants.PAGE_SIZE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCacheGettyInfoUseCase: GetCacheGettyInfoUseCase,
) : ViewModel() {

    private val TAG = this::class.java.simpleName

    private var loadPage = 1

    private var _mainState: MutableStateFlow<MainUiState> = MutableStateFlow(MainUiState.None)
    val mainState get() = _mainState.asStateFlow()


    // 1. Room 조회
    fun callGettyDatabase() = viewModelScope.launch(Dispatchers.IO) {
        Log.d(TAG, "callGettyDatabase: page = $loadPage")
        runCatching {
            var prevPage = if (loadPage <= 1) 1 else loadPage - 1
            getCacheGettyInfoUseCase.invoke(prevPage, loadPage).collect {
                if (it is MainUiState.Success) {
                    if (it.uiDataList.isNotEmpty()) {
                        loadPage = it.uiDataList.size / PAGE_SIZE
                        prevPage = loadPage - 1
                    }
                    loadPage++
                }
                updateMainState(it)
            }

        }.onFailure {
            updateMainState(MainUiState.Fail(it))
        }
    }


    private suspend fun updateMainState(state: MainUiState) = viewModelScope.launch {
        Log.d(TAG, "updateMainState-(${state::class.java.simpleName})")
        _mainState.emit(state)
    }
}