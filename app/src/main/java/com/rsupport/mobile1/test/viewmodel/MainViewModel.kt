package com.rsupport.mobile1.test.viewmodel

import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rsupport.mobile1.test.server.ApiConnector
import com.rsupport.mobile1.test.server.resp.ImageData
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.net.URL

class MainViewModel (application: Application) : AndroidViewModel(application) {
    private val _imageDataList = MutableLiveData<ArrayList<ImageData>>()
    val imageDataList: LiveData<ArrayList<ImageData>>
        get() = _imageDataList

    //API 통신 클래스
    private val mApiConnector: ApiConnector = ApiConnector.getInstance(application)

    fun getImageList() {
        mApiConnector.searchImages(
            onSuccess = { resp ->
                resp?.images?.let { images ->
                    _imageDataList.postValue(images)
                }
            },
            onFailed = {

            },
            "collaboration",
            1,
            20)
    }
}