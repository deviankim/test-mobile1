package com.rsupport.mobile1.test.activity.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.map
import androidx.recyclerview.widget.GridLayoutManager
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.rsupport.mobile1.test.activity.App.Companion.imageLoader
import com.rsupport.mobile1.test.activity.data.network.WebScrapper
import com.rsupport.mobile1.test.activity.data.repository.MainRepository
import com.rsupport.mobile1.test.activity.ui.main.adapters.MainLoadStateAdapter
import com.rsupport.mobile1.test.activity.ui.main.adapters.MainPagingAdapter
import com.rsupport.mobile1.test.activity.util.Constants
import com.rsupport.mobile1.test.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel> {
        MainViewModel.provideFactory(repository = MainRepository(WebScrapper()))
    }
    private val pagingAdapter: MainPagingAdapter by lazy { MainPagingAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this

        setListAdapter()
        setErrorAndLoadingHandlers()
    }

    private fun setErrorAndLoadingHandlers() {
        binding.apply {
            btnRetry.setOnClickListener { pagingAdapter.retry() }

            pagingAdapter.addLoadStateListener { loadState ->
                groupError.isVisible = loadState.refresh is LoadState.Error
                progressBar.isVisible = loadState.refresh is LoadState.Loading
            }
        }
    }

    private fun setListAdapter() {
        setRecyclerViewAdapter()
        setRecyclerView()

        lifecycleScope.launch {
            viewModel.items.map { pagingData ->
                pagingData.map { imageInfo ->
                    preloadImage(imageInfo.imageUrl)
                    imageInfo
                }
            }.collectLatest { pagingData ->
                pagingAdapter.submitData(pagingData)
            }
        }
    }

    private fun preloadImage(imageUrl: String) {
        val request = ImageRequest.Builder(this)
            .data(imageUrl)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .diskCachePolicy(CachePolicy.ENABLED)
            .memoryCacheKey(imageUrl)
            .diskCacheKey(imageUrl)
            .build()
        imageLoader.enqueue(request)
    }

    private fun setRecyclerViewAdapter() {
        binding.rvImageList.adapter =
            pagingAdapter.withLoadStateFooter(MainLoadStateAdapter { pagingAdapter.retry() })
    }

    private fun setRecyclerView() {
        binding.rvImageList.apply {
            layoutManager = GridLayoutManager(baseContext, 3).apply {
                spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return when (pagingAdapter.getItemViewType(position)) {
                            Constants.CONTENTS_TYPE -> 3
                            Constants.LOAD_STATE_TYPE -> 1
                            else -> 1
                        }
                    }
                }
            }
        }
    }
}