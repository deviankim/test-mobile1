package com.rsupport.mobile1.test.activity.presenter

import androidx.lifecycle.*
import com.rsupport.mobile1.test.activity.domain.GettyImageRepositoryImpl
import com.rsupport.mobile1.test.activity.domain.GettyImageResponse
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext

class MainViewModel(private val gettyImageRepository: GettyImageRepositoryImpl) : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = viewModelScope.coroutineContext

    private val _gettyImageList = MutableLiveData<List<GettyImageResponse>>()
    val gettyImageList: LiveData<List<GettyImageResponse>>
        get() = _gettyImageList

    fun requestImage() {
        gettyImageRepository.getImage()
    }

    class Factory(private val gettyImageRepository: GettyImageRepositoryImpl) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(gettyImageRepository) as T
        }
    }

}