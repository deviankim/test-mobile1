package com.example.image_loader.request.delegate

import androidx.annotation.MainThread

internal interface RequestDelegate {

    /** 이미지 뷰가 attached가 되지 않았으면 request만 설정하고 [CancellationException] 예외 발생. */
    @MainThread
    fun setNewRequest() {}

    /** 이미지 뷰가 LifeCycle.Started 될 때까지 중지. */
    @MainThread
    suspend fun awaitStarted() {}

    /** [ImageRequestJob]의 job이 cancelled or completed 되면 호출. */
    @MainThread
    fun complete() {}

    /** [ImageRequestJob]의 job이 cancelled 되었을 때 호출. */
    @MainThread
    fun dispose() {}
}
