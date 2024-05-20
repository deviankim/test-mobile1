package com.kimwijin.presentation.ui

import android.os.Bundle
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import com.kimwijin.presentation.R
import com.kimwijin.presentation.databinding.ActivityMainBinding
import com.kimwijin.presentation.util.ext.text
import com.kimwijin.presentation.util.ext.toastShort
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val backPressEvent = MutableSharedFlow<Unit>(
        replay = 0,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleOnBack()
    }

    private fun handleOnBack() {
        onBackPressedDispatcher.addCallback {
            backPressEvent.tryEmit(Unit)
        }

        MainScope().launch {
            backPressEvent
                .scan(listOf(System.currentTimeMillis() - 2000)) { acc, _ ->
                    acc.takeLast(1) + System.currentTimeMillis()
                }
                .drop(1)
                .collectLatest {
                    if (it.last() - it.first() < 2000) {
                        finishAffinity()
                    } else {
                        this@MainActivity.toastShort(this@MainActivity.text(R.string.go_to_finish))
                    }
                }
        }
    }
}