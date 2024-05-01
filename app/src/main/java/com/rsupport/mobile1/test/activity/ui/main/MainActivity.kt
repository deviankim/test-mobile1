package com.rsupport.mobile1.test.activity.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.rsupport.mobile1.test.activity.data.network.WebScrapper
import com.rsupport.mobile1.test.activity.data.repository.MainRepository
import com.rsupport.mobile1.test.activity.ui.main.adapters.MainPagingAdapter
import com.rsupport.mobile1.test.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
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
            viewModel.items.collectLatest {
                pagingAdapter.submitData(it)
            }
        }
    }

    private fun setRecyclerViewAdapter() {
        binding.rvImageList.adapter = pagingAdapter
    }

    private fun setRecyclerView() {
        binding.rvImageList.apply {
            layoutManager = GridLayoutManager(baseContext, 3)
        }
    }
}