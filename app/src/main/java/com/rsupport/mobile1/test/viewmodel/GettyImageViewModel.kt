package com.rsupport.mobile1.test.viewmodel

import androidx.lifecycle.*
import com.rsupport.mobile1.test.domain.GetGettyImageUseCase
import com.rsupport.mobile1.test.network.Uistate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GettyImageViewModel @Inject constructor(
    private val getGettyImageUseCase: GetGettyImageUseCase
) : ViewModel() {
    private var pageIndex = 1
    private val _uiStateLiveData = MutableLiveData<Uistate>()
    val uiStateLiveData: LiveData<Uistate>
        get() = _uiStateLiveData

    init {
        loadImages(pageIndex)
    }

    private fun loadImages(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiStateLiveData.postValue(Uistate.Loading)
            try {
                val data = getGettyImageUseCase.getGettyImages(page).toList()
                _uiStateLiveData.postValue(Uistate.Success(data))
            } catch (e: Exception) {
                _uiStateLiveData.postValue(Uistate.Fail(e))
            }
        }
    }

    fun loadNextPage() {
        pageIndex++
        loadImages(pageIndex)
    }
}