package com.rsupport.mobile1.test.activity.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rsupport.mobile1.test.activity.domain.GettyImageRepositoryImpl

class MainViewModel : ViewModel() {

    private val _gettyImageList = MutableLiveData<List<Any>>()
    val gettyImageList: LiveData<List<Any>>
        get() = _gettyImageList

    fun requestImage() {
        GettyImageRepositoryImpl().getImage()
    }

}