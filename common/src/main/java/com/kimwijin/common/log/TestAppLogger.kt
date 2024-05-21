package com.kimwijin.common.log

import android.util.Log

/**
 * 애플리케이션 전역에 걸쳐 사용 되는 로깅 유틸리티 클래스 입니다.
 * 이 클래스는 다양한 로그 레벨(예: ERROR, WARN, INFO, DEBUG)에 따른 메시지를 처리합니다.
 * 로그 레벨 설정을 통해 출력을 필터링 할 수 있어, 개발 또는 운영 환경에 맞게 유연하게 로깅할 수 있습니다.
 * 싱글턴 패턴을 사용하여 리소스 사용을 최적화하고, 전역에서 접근 가능하게 관리합니다.
 *
 * 사용 예:
 * TestAppLogger.e("This is an error message");
 *
 * @author (김위진)
 * @since (2024-05-20)
 */
object TestAppLogger {

    private val TAG = "[TestAppLogger]"

    fun d(msg: String) {
        Log.d(TAG, msg)
    }

    fun d(tag: String, msg: String) {
        Log.d(tag, msg)
    }

    fun e(msg: String) {
        Log.e(TAG, msg)
    }

    fun e(tag: String, msg: String, throwable: Throwable?) {
        Log.e(tag, msg, throwable)
    }

    fun i(msg: String) {
        Log.i(TAG, msg)
    }

    fun i(tag: String, msg: String) {
        Log.i(tag, msg)
    }

    fun w(msg: String) {
        Log.w(TAG, msg)
    }

    fun w(tag: String, msg: String) {
        Log.w(tag, msg)
    }
}