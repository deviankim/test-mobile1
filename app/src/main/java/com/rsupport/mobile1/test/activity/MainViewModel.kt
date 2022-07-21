package com.rsupport.mobile1.test.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _gettyImageList = MutableLiveData<List<Any>>()
    val gettyImageList: LiveData<List<Any>>
        get() = _gettyImageList

}