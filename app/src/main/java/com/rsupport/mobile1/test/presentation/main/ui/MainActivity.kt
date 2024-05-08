package com.rsupport.mobile1.test.presentation.main.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.rsupport.mobile1.test.R
import com.rsupport.mobile1.test.databinding.ActivityMainBinding
import com.rsupport.mobile1.test.presentation.main.ui.list.MainRecyclerViewAdapter
import com.rsupport.mobile1.test.presentation.main.ui.list.MainRecyclerViewAdapter.Companion.VIEW_TYPE_BOTTOM
import com.rsupport.mobile1.test.presentation.main.ui.list.MainRecyclerViewAdapter.Companion.VIEW_TYPE_LIST
import com.rsupport.mobile1.test.presentation.main.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.jsoup.HttpStatusException
import java.io.IOException
import java.net.UnknownHostException

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    private lateinit var mainRecyclerViewAdapter: MainRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        initMainRecyclerView()
        setViewModelObserve()
        observeMainUiState()

        binding.btnRetry.setOnClickListener {
            viewModel.currentPage.value?.let { viewModel.getMainInfo(it) }
        }
    }

    private fun observeMainUiState() {
        lifecycleScope.launch {
            viewModel.mainUiState
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { uiState ->
                    when (uiState) {
                        is MainUiState.Loading -> {
                            binding.pbLoading.visibility = View.VISIBLE
                        }

                        is MainUiState.Success -> {
                            mainRecyclerViewAdapter.submitList(uiState.data.contents)

                            binding.pbLoading.visibility = View.GONE
                            binding.clWifiInstability.visibility = View.GONE
                        }

                        is MainUiState.Error -> {
                            binding.pbLoading.visibility = View.GONE

                            when (uiState.error) {
                                is HttpStatusException -> {
                                    Toast.makeText(
                                        this@MainActivity,
                                        "원하시는 페이지를 찾을 수 없습니다.",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                                is UnknownHostException -> {
                                    binding.clWifiInstability.visibility = View.VISIBLE
                                }

                                else -> {
                                    throw IOException()
                                }
                            }
                        }
                    }
                }
        }
    }

    private fun setViewModelObserve() = with(viewModel) {
        currentPage.observe(this@MainActivity) {
            viewModel.getMainInfo(it)
            hideKeyboard()
        }
    }

    private fun hideKeyboard() {
        if (currentFocus == null) return
        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(
            currentFocus?.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }

    private fun initMainRecyclerView() {
        mainRecyclerViewAdapter = MainRecyclerViewAdapter(
            mainBottomListener = object : MainRecyclerViewAdapter.MainBottomListener {
                override fun goToPreviousPage() {
                    viewModel.decreasePageNumber()
                }

                override fun goToNextPage() {
                    viewModel.increasePageNumber()
                }

                override fun goToSetPage(pageNumber: Int) {
                    viewModel.setPageNumber(pageNumber)
                }
            }
        )
        binding.rvMainList.adapter = mainRecyclerViewAdapter
        binding.rvMainList.layoutManager = createGridLayoutManager()
    }

    private fun createGridLayoutManager(): GridLayoutManager {
        return GridLayoutManager(this, 3).apply {
            spanSizeLookup = createSpanSizeLookup(spanCount)
        }
    }

    private fun createSpanSizeLookup(spanCount: Int): GridLayoutManager.SpanSizeLookup {
        return object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (mainRecyclerViewAdapter.getItemViewType(position)) {
                    VIEW_TYPE_LIST -> 1
                    VIEW_TYPE_BOTTOM -> spanCount
                    else -> throw IllegalArgumentException("ViewType is not valid")
                }
            }
        }
    }
}