package com.example.image_loader.request

import androidx.annotation.MainThread
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.example.image_loader.callbacks.RestartCallback
import com.example.image_loader.request.delegate.RequestDelegate
import com.example.image_loader.util.awaitStarted
import com.example.image_loader.util.getLifecycle
import kotlinx.coroutines.Job
import java.util.concurrent.CancellationException

/** 이미지 로딩 작업 상태인 Job을 관리하는 Wraaping 클래스 */
internal class ImageRequestJob(
    private val request: ImageRequest,
    private val imageLoadJob: Job,
    private val restartCallback: RestartCallback
) : RequestDelegate, DefaultLifecycleObserver {

    private val target = request.target
    private val lifecycle = target.context.getLifecycle()

    override fun setNewRequest() {
        if (!target.isAttachedToWindow) {
            target.requestManager.setRequestJob(this)
            throw CancellationException("'request.target' must be attached to a window.")
        }
    }

    @MainThread
    override suspend fun awaitStarted() {
        lifecycle?.addObserver(this)
        lifecycle?.awaitStarted()
    }

    @MainThread
    override fun complete() {
        lifecycle?.removeObserver(this)
    }

    @MainThread
    override fun dispose() {
        imageLoadJob.cancel()
        lifecycle?.removeObserver(this)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        target.requestManager.dispose()
    }

    fun restart() {
        restartCallback.restart(request)
    }
}
