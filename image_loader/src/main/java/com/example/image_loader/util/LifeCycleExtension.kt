package com.example.image_loader.util

import android.content.Context
import android.content.ContextWrapper
import androidx.annotation.MainThread
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.State.STARTED
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@MainThread
internal suspend fun Lifecycle.awaitStarted() {
    if (currentState.isAtLeast(STARTED)) return

    var observer: LifecycleObserver? = null
    try {
        suspendCoroutine { continuation ->
            observer = object : DefaultLifecycleObserver {
                override fun onStart(owner: LifecycleOwner) {
                    continuation.resume(Unit)
                }
            }
            addObserver(observer!!)
        }
    } finally {
        observer?.let(::removeObserver)
        observer = null
    }
}

fun Context?.getLifecycle(): Lifecycle? {
    var context: Context? = this
    while (true) {
        when (context) {
            is LifecycleOwner -> return context.lifecycle
            !is ContextWrapper -> return null
            else -> context = context.baseContext
        }
    }
}
