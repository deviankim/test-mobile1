package com.kimwijin.presentation.util.ext

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

/**
 * 확장 함수를 지원하는 클래스입니다.
 *
 * @author (김위진)
 * @since (2024-05-20)
 */
fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Context.toastShort(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Context.toastLong(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()

fun Context.text(resId: Int) =
    this.getText(resId).toString()

fun Context.text(resId: Int, value: String) =
    this.getString(resId, value)

fun Context.color(resId: Int) =
    ContextCompat.getColor(this, resId)

fun Context.isNetworkAvailable(): Boolean {
    val connectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    val now = connectivityManager.activeNetwork ?: return false
    val actNw = connectivityManager.getNetworkCapabilities(now) ?: return false

    return actNw.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
}