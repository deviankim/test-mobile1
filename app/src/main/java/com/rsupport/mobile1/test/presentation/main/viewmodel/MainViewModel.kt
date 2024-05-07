package com.rsupport.mobile1.test.presentation.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rsupport.mobile1.test.domain.model.HtmlParseResult
import com.rsupport.mobile1.test.domain.model.MainList
import com.rsupport.mobile1.test.domain.usecase.MainUseCase
import com.rsupport.mobile1.test.presentation.main.ui.MainUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainUseCase: MainUseCase,
) : ViewModel() {

    private val _mainUiState = MutableStateFlow<MainUiState<MainList>>(MainUiState.Loading)
    val mainUiState = _mainUiState.asStateFlow()

    init {
        getMainInfo()
    }

    private fun getMainInfo() {
        _mainUiState.value = MainUiState.Loading

        viewModelScope.launch {
            mainUseCase().collectLatest {
                when (it) {
                    is HtmlParseResult.Success -> {
                        _mainUiState.value = MainUiState.Success(it.data)
                    }

                    is HtmlParseResult.Error -> {
                        _mainUiState.value =
                            MainUiState.Error(IllegalStateException(it.exception.message))
                    }
                }
            }
        }
    }
}