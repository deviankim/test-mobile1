package com.rsupport.mobile1.test.presentation.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rsupport.mobile1.test.domain.model.HtmlParseResult
import com.rsupport.mobile1.test.domain.model.MainList
import com.rsupport.mobile1.test.domain.model.MainRecyclerViewItem
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

    private val _currentPage = MutableLiveData<Int>(1)
    val currentPage: LiveData<Int> = _currentPage

    private val _mainUiState = MutableStateFlow<MainUiState<MainList>>(MainUiState.Loading)
    val mainUiState = _mainUiState.asStateFlow()

    fun getMainInfo(pageNumber: Int) {
        _mainUiState.value = MainUiState.Loading

        viewModelScope.launch {
            mainUseCase(pageNumber).collectLatest { htmlParseResult ->
                when (htmlParseResult) {
                    is HtmlParseResult.Success -> {
                        val updatedContents = htmlParseResult.data.contents.map { item ->
                            when (item) {
                                is MainRecyclerViewItem.PageNumber -> item.copy(
                                    pageNumber = _currentPage.value ?: 1
                                )

                                else -> item
                            }
                        }
                        htmlParseResult.data.copy(contents = updatedContents).also { updatedData ->
                            _mainUiState.value = MainUiState.Success(updatedData)
                        }
                    }

                    is HtmlParseResult.Error -> {
                        _mainUiState.value =
                            MainUiState.Error(IllegalStateException(htmlParseResult.exception.message))
                    }
                }
            }
        }
    }

    fun decreasePageNumber() {
        _currentPage.value = _currentPage.value?.dec()
    }

    fun increasePageNumber() {
        _currentPage.value = _currentPage.value?.inc()
    }

    fun setPageNumber(pageNumber: Int) {
        _currentPage.value = pageNumber
    }
}