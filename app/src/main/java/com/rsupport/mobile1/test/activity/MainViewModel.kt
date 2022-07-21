package com.rsupport.mobile1.test.activity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _gettyImageList = MutableLiveData<List<Any>>()
    val gettyImageList: LiveData<List<Any>>
        get() = _gettyImageList

    fun getImage() {
        // TODO : 이미지 데이터 수신부 구현 예정
        Log.d(MainActivity.TAG, "TEST OK")
    }

}