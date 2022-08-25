package com.rsupport.mobile1.test.presenter.activity

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rsupport.mobile1.test.domain.GetGettyInfoUseCase
import com.rsupport.mobile1.test.domain.cache.GetCacheGettyInfoUseCase
import com.rsupport.mobile1.test.presenter.activity.ui_state.MainUiState
import com.rsupport.mobile1.test.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val getGettyInformationUseCase: GetGettyInfoUseCase,
    private val getCacheGettyInfoUseCase: GetCacheGettyInfoUseCase,
) : ViewModel() {

    private val TAG = this::class.java.simpleName

    private var _mainState: MutableStateFlow<MainUiState> = MutableStateFlow(MainUiState.None)
    val mainState get() = _mainState.asStateFlow()


    fun callGettyImage() = viewModelScope.launch(Dispatchers.IO) {
        Log.d(TAG, "callGettyImage-()")
        runCatching {
            val elements = Jsoup.connect(String.format(Constants.WEB_URL, 1)).get()
                .select("div.gallery-asset-schema div")
            getGettyInformationUseCase.invoke(elements)
                .collect {
                    updateMainState(it)
                }
        }.onFailure { throwable ->
            try {
                getCacheGettyInfoUseCase.invoke().collect {
                    Log.i(TAG, "callGettyImage: ${it::class.java.simpleName}")
                    if (it is MainUiState.Success) {
                        if (it.uiDataList.isNotEmpty()) {
                            updateMainState(it)
                        } else {
                            updateMainState(MainUiState.Fail(throwable))
                        }
                    }
                }
            } catch (e: Exception) {
                updateMainState(MainUiState.Fail(e))
            }
        }
    }


    private suspend fun updateMainState(state: MainUiState) = viewModelScope.launch {
        Log.d(TAG, "updateMainState-(${state::class.java.simpleName})")
        _mainState.emit(state)
    }
}