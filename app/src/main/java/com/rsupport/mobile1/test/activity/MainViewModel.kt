package com.rsupport.mobile1.test.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.rsupport.mobile1.test.model.Image
import com.rsupport.mobile1.test.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val imageRepository: ImageRepository
): ViewModel() {
    val isLoading: MutableLiveData<Boolean> = MutableLiveData(true)
    val isError: MutableLiveData<Boolean> = MutableLiveData(false)

    private val imageFlow: MutableStateFlow<List<Image>> = MutableStateFlow(emptyList())
    val imageLiveData = imageFlow.asLiveData()

    init {
        fetchImageList(1)
    }

    fun fetchImageList(page: Int) {
        viewModelScope.launch {
            imageRepository.getImages(page = page)
                .onStart {
                    isLoading.postValue(true)
                }
                .onCompletion {
                    isLoading.postValue(false)
                    isError.postValue(false)
                }
                .catch {
                    isLoading.postValue(false)
                    isError.postValue(true)
                }
                .collectLatest {
                    imageFlow.value = it
                }
        }
    }
}