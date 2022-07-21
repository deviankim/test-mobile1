package com.rsupport.mobile1.test.activity.presenter

import android.util.Log
import androidx.lifecycle.*
import com.rsupport.mobile1.test.activity.domain.GettyImageRepositoryImpl
import com.rsupport.mobile1.test.activity.domain.GettyImageResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class MainViewModel @Inject constructor(private val gettyImageRepository: GettyImageRepositoryImpl) : ViewModel(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = viewModelScope.coroutineContext

    private val _gettyImageList = MutableLiveData<List<GettyImageResponse>?>()
    val gettyImageList: LiveData<List<GettyImageResponse>?>
        get() = _gettyImageList

    fun requestImage() = launch (coroutineContext + Dispatchers.IO + CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.e(MainActivity.TAG, "Coroutine Exception : ${throwable.message}")
        Log.e(MainActivity.TAG, "Coroutine Exception : ${throwable.stackTrace}")
    }) {
        val result = gettyImageRepository.getImage()
        if (result != null) {
            _gettyImageList.postValue(result)
        } else {
            Log.e(MainActivity.TAG, "result data is empty")
        }
    }
}