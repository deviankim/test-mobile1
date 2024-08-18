package com.rsupport.mobile1.test.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.rsupport.mobile1.test.databinding.ActivityMainBinding
import com.rsupport.mobile1.test.ui.state.UiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        subscribe()
        viewModel.requestApi()
    }

    private fun subscribe() {
        viewModel.state.observe(this) { state ->
            when (state) {
                is UiState.Uninitialized -> {
                    binding.activityMainText.text = "Hello RSUPPORT"
                }

                is UiState.Loading -> {

                }

                is UiState.Empty -> {


                }

                is UiState.Success -> {
                    binding.activityMainText.text = state.data.toString()
                }

                is UiState.Error -> {


                }
            }
        }
    }
}