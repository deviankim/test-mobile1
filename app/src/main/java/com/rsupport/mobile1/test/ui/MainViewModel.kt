package com.example.crawlin_test.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crawlin_test.data.model.ImageData
import com.example.crawlin_test.data.response.NetworkResult
import com.example.crawlin_test.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val imageRepository: ImageRepository) : ViewModel() {

    val _imgList = MutableLiveData<List<ImageData>>()
    val imgList: LiveData<List<ImageData>> get() = _imgList

    //ProgressBar Binding
    val errMsg: MutableLiveData<String> by lazy { MutableLiveData<String>().apply { postValue("") } }
    val isLoading: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>().apply { postValue(false) } }

    init {
        crawling()
    }

    fun crawling() {
        viewModelScope.launch {
            isLoading.postValue(true)
            withContext(Dispatchers.IO) {
                launch {
                    imageRepository.requestCrawlingImage().collectLatest { result ->
                        when (result) {
                            is NetworkResult.Success -> {
                                _imgList.postValue(result.value)
                            }

                            is NetworkResult.Empty -> {
                                errMsg.postValue("data empty.")
                            }

                            is NetworkResult.Error -> {
                                errMsg.postValue(result.exception?.message.toString())
                            }
                        }
                    }
                }
            }
            isLoading.postValue(false)
        }
    }



}