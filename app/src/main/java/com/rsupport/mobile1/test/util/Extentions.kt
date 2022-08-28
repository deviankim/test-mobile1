package com.rsupport.mobile1.test.util

import android.content.res.Resources
import android.view.View
import androidx.annotation.RestrictTo

/** View visibility */
fun View.show(isShow: Boolean? = null) {
    if (isShow == false) this.visibility = View.GONE
    else this.visibility = View.VISIBLE
}

fun View.gone() = run { visibility = View.GONE }
fun View.hide() = run { visibility = View.INVISIBLE }

/** Convert px to dp */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
fun Float.dp(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
fun Int.dp(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
fun Float.toDp(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
fun Int.toDp(): Float = (this * Resources.getSystem().displayMetrics.density)
