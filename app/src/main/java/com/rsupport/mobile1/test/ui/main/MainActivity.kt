package com.rsupport.mobile1.test.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.rsupport.mobile1.test.R
import com.rsupport.mobile1.test.databinding.ActivityMainBinding
import com.rsupport.mobile1.test.domain.model.Image
import com.rsupport.mobile1.test.ui.main.adapter.ImageAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private val imageAdapter = ImageAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.apply {
            lifecycleOwner = this@MainActivity
            vm = viewModel
            imageAdapter = this@MainActivity.imageAdapter
        }

        setUpPagingAdapter()
    }



    private fun setUpPagingAdapter() {
//         initial loading & error handling
        imageAdapter.addLoadStateListener { loadState ->
            when(loadState.refresh) {
                is LoadState.Loading -> {
                    binding.pbLoading.visibility = View.VISIBLE
                    binding.tvLoadError.visibility = View.GONE
                    binding.btnRetry.visibility = View.GONE
                }
                is LoadState.Error -> {
                    binding.pbLoading.visibility = View.GONE
                    binding.tvLoadError.visibility = View.VISIBLE
                    binding.btnRetry.visibility = View.VISIBLE
                }
                else -> {
                    binding.pbLoading.visibility = View.GONE
                    binding.tvLoadError.visibility = View.GONE
                    binding.btnRetry.visibility = View.GONE
                }
            }
        }

        // load data
        lifecycleScope.launch {
            viewModel.imagePagingDataFlow
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collectLatest {
                    imageAdapter.submitData(it)
                }
        }
    }


}