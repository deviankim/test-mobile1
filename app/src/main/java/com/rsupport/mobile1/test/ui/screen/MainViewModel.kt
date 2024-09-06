package com.rsupport.mobile1.test.ui.screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rsupport.mobile1.test.common.ErrorMessages
import com.rsupport.mobile1.test.common.Resource
import com.rsupport.mobile1.test.domain.use_case.GetImageUrlsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getImageUrlsUseCase: GetImageUrlsUseCase) : ViewModel()  {
    private val _state = mutableStateOf(MainState())
    val state: State<MainState> = _state

    init {
        getImageUrls(phrase = INITIAL_PHRASE)
    }

    private fun getImageUrls(phrase: String){
        getImageUrlsUseCase(phrase = phrase).onEach { result ->
            when(result){
                is Resource.Success -> {
                    _state.value = MainState(urls = result.data.orEmpty())
                }
                is Resource.Error -> {
                    _state.value = MainState(errorMessage = result.message.orEmpty())
                }
                is Resource.Loading -> {
                    _state.value = MainState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    companion object {
        const val INITIAL_PHRASE = "collaboration"
    }
}