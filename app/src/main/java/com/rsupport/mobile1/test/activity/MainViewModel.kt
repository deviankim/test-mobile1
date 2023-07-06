package com.rsupport.mobile1.test.activity

import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.rsupport.mobile1.test.model.ImageSrc
import com.rsupport.mobile1.test.repository.GettyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class MainViewModel @Inject constructor(
    private val gettyRepository: GettyRepository
): ViewModel() {
    val isLoading: MutableLiveData<Boolean> = MutableLiveData(true)
    private val imageFetchIndex: MutableStateFlow<Int> = MutableStateFlow(1)

    val imageFlow: LiveData<List<ImageSrc>> = imageFetchIndex.flatMapLatest {page ->
        Timber.d("page index : $page")
        gettyRepository.getImages(
            page = page,
        onStart = {
            isLoading.postValue(true)
        },
        onComplete = {
            isLoading.postValue(false)
        },
        onError = {
            isLoading.postValue(false)
        })
    }.asLiveData()

    @MainThread
    fun fetchNextImageList() {
        imageFetchIndex.value++
    }

    @MainThread
    fun fetchIndexInit() {
        imageFetchIndex.value = 0
    }
}