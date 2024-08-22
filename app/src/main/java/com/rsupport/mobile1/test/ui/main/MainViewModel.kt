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
    private var page = 1

    fun nextPage() {
        page++
        fetchCollaborationPhoto()
    }

    fun refresh() {
        page = 1
        fetchCollaborationPhoto()
    }

    fun fetchCollaborationPhoto() {
        repository.fetchCollaborationPhoto(page)
            .subscribe({ response ->
                _state.postValue(UiState.Success(response.data, response.isMore))
            }, { t: Throwable? ->
                _state.postValue(UiState.Error())
            })
            .apply { compositeDisposable.add(this) }
    }
}