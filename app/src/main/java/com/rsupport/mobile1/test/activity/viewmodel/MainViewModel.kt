package com.rsupport.mobile1.test.activity.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rsupport.mobile1.test.activity.data.Images
import com.rsupport.mobile1.test.activity.repository.ImageListRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val imageListRepository: ImageListRepository) : BaseViewModel() {

    private val _mImageList = MutableLiveData<ArrayList<Images>>()
    val mImageList: LiveData<ArrayList<Images>> get() = _mImageList

    init {
        // 뷰모델 생성 시 호출
        requestImageList()
    }

    /**
     * 이미지 데이터를 가져온다.
     */
    fun requestImageList() {
        addDisposable(
            imageListRepository.requestImageElements()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe{ showDialog() }
                .doAfterTerminate{ dissmissDialog() }
                .subscribe({
                    _mImageList.value = it
                }, {
                    Log.d("Throwable!", "이미지 생성에 실패하였습니다." + it.cause.toString())
                })
        )
    }
}