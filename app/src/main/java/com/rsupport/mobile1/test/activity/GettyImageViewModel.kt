package com.rsupport.mobile1.test.activity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class GettyImageViewModel : ViewModel() {

    // TODO
    private val gettyImageRepository = GettyImageRepository()

    private var _loadImageList = MutableLiveData<Resource<List<GettyImage>>>()
    val loadImageList: LiveData<Resource<List<GettyImage>>> = _loadImageList

    fun loadGettyImageList() = viewModelScope.launch {
        try {
            _loadImageList.postValue(Resource.Loading)
            val gettyImageList = gettyImageRepository.fetchGettyImageList()
            Log.d("jenny", "loadGettyImageList, gettyImageList.size: ${gettyImageList.size}")
            _loadImageList.postValue(Resource.Success(gettyImageList))
        } catch (e: Exception) {
            _loadImageList.postValue(Resource.Failure(e))
        }
    }
}