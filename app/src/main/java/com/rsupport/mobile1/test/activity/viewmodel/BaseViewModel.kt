package com.rsupport.mobile1.test.activity.viewmodel

import androidx.lifecycle.ViewModel
import com.rsupport.mobile1.test.activity.LoadingDialogType
import com.rsupport.mobile1.test.activity.SingleLiveEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Base ViewModel
 * Dispose 정의
 * 각 뷰모델에서 발행되고 등록된 리소스를 해제할 수 있다.
 */
open class BaseViewModel : ViewModel() {
    val startLoadingDialogState = SingleLiveEvent<LoadingDialogType>()

    protected val compositeDisposable = CompositeDisposable()

    /**
     * Observer add
     */
    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    /**
     * add된 Observer 해지
     */
    fun clearDisposable() {
        compositeDisposable.clear()
    }

    override fun onCleared() {
        super.onCleared()
        if (compositeDisposable.isDisposed) {
            // 리소스가 제대로 폐기됬는지 확인한 후 리소스 폐기한다.
            compositeDisposable.dispose()
        }
    }

    fun showDialog() {
        startLoadingDialogState.postValue(LoadingDialogType.SHOW)
    }

    fun dissmissDialog() {
        startLoadingDialogState.postValue(LoadingDialogType.DISMISS)
    }
}
