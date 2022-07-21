package com.rsupport.mobile1.test.activity.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rsupport.mobile1.test.activity.domain.GettyImageRepositoryImpl
import com.rsupport.mobile1.test.activity.domain.GettyImageResponse

class MainViewModel : ViewModel() {

    private val _gettyImageList = MutableLiveData<List<GettyImageResponse>>()
    val gettyImageList: LiveData<List<GettyImageResponse>>
        get() = _gettyImageList

    fun requestImage() {
        GettyImageRepositoryImpl().getImage()
    }

}