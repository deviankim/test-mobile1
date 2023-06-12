package com.rsupport.mobile1.test.activity

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

class SingleLiveEvent<T> : MutableLiveData<T>() {
    // setValue로 새로운 이벤트를 받으면 true로 바뀌고 그 이벤트가 실행되면 false로 돌아감
    private val isPending = AtomicBoolean(false)

    @MainThread
    override fun setValue(value: T?) {
        isPending.set(true)
        super.setValue(value)
    }

    // isPending 를 관찰하여 true 일 경우 false 로 바꾸고 이벤트 호출 알림
    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, Observer {
            if (isPending.compareAndSet(true, false)) {
                observer.onChanged(it)
            }
        })
    }

    // T가 Void 일 경우 호출을 편하게 하기 위해 있는 함수
    @MainThread
    fun call() {
        value = null
    }
}