package com.kimwijin.presentation.util.ext

import android.view.View
import android.widget.TextView

/**
 * View 클래스의 확장 함수를 지원하는 클래스입니다.
 *
 * @author (김위진)
 * @since (2024-05-20)
 */
fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.setVisibility(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}


fun TextView.getString(): String {
    return this.text?.trim().toString()
}