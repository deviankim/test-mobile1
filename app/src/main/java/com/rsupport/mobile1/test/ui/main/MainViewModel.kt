package com.rsupport.mobile1.test.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rsupport.mobile1.test.base.BaseViewModel
import com.rsupport.mobile1.test.data.PhotoRepository
import com.rsupport.mobile1.test.data.domain.Photo
import com.rsupport.mobile1.test.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: PhotoRepository): BaseViewModel() {

    private val _state = MutableLiveData<UiState<List<Photo>>>(UiState.Uninitialized)
    val state: LiveData<UiState<List<Photo>>> get() = _state

    fun requestApi() {
        _state.postValue(UiState.Loading)

        repository.fetchCollaborationPhoto()
            .subscribe({ photoList ->
                _state.postValue(UiState.Success(photoList))
            }, { t: Throwable? ->
                _state.postValue(UiState.Error())
            })
            .apply { compositeDisposable.add(this) }
    }
}