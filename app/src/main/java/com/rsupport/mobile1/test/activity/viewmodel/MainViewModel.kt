package com.rsupport.mobile1.test.activity.viewmodel

import android.text.Html
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rsupport.mobile1.test.activity.api.Resource
import com.rsupport.mobile1.test.activity.data.repository.WebRepository
import com.rsupport.mobile1.test.activity.data.vo.ImageData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel(){

    @Inject
    lateinit var webRepo: WebRepository

    var index = 0
    val resultHtml = MutableLiveData<String>()
    var _imageList = MutableLiveData<ArrayList<ImageData>>()
    val imageList : LiveData<ArrayList<ImageData>> get() = _imageList

    fun getImageData(){
        GlobalScope.async(Dispatchers.Main) {
            when (val response = webRepo.getPhotoCollaboration()) {
                is Resource.Success -> {
                    val result = response.value.string()
                    resultHtml.value = result
                }
                else -> Log.e("TEST", "request fail : $response")
            }
        }
    }
}