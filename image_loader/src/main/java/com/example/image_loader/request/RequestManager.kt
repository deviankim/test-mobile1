package com.example.image_loader.request

import android.view.View
import android.widget.ImageView
import androidx.annotation.MainThread
import com.example.image_loader.R
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/** 이미지 뷰와 연결된 [RequestManager]를 가져오기 위한 Extension */
internal val ImageView.requestManager: RequestManager
    get() {
        var manager = getTag(R.id.request_manager) as? RequestManager
        if (manager == null) {
            manager = synchronized(this) {
                (getTag(R.id.request_manager) as? RequestManager)
                    ?.let { return@synchronized it }

                RequestManager().apply {
                    addOnAttachStateChangeListener(this)
                    setTag(R.id.request_manager, this)
                }
            }
        }
        return manager
    }

/** Attached 상태에 따라 현재 작업중인 [ImageRequestJob]의 실행과 종료를 관리하는 클래스. */
internal class RequestManager : View.OnAttachStateChangeListener {

    private var currentRequest: ImageRequestJob? = null
    private var pendingClear: Job? = null

    @MainThread
    fun setRequestJob(request: ImageRequestJob?) {
        currentRequest?.dispose()
        currentRequest = request
    }

    @Synchronized
    @OptIn(DelicateCoroutinesApi::class)
    fun dispose() {
        pendingClear?.cancel()
        pendingClear = GlobalScope.launch(Dispatchers.Main.immediate) { setRequestJob(null) }
    }

    /** onViewAttachedToWindow()가 호출되었고 request가 존재한다면 이미지 로딩을 재시작 */
    @MainThread
    override fun onViewAttachedToWindow(v: View) {
        val request = currentRequest ?: return
        request.restart()
    }

    /** onViewDetachedFromWindow()가 호출되면 이미지 로딩 작업을 취소 */
    @MainThread
    override fun onViewDetachedFromWindow(v: View) {
        dispose()
    }
}
