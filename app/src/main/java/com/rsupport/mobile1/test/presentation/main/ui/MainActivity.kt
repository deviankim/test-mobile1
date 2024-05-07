package com.rsupport.mobile1.test.presentation.main.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.rsupport.mobile1.test.R
import com.rsupport.mobile1.test.databinding.ActivityMainBinding
import com.rsupport.mobile1.test.presentation.main.ui.list.MainListAdapter

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    private lateinit var mainListAdapter: MainListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initTodoListRecyclerView()
        observeMainUiState()
    }

    private fun observeMainUiState() {
        lifecycleScope.launch {
            viewModel.mainUiState
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { uiState ->
                    when (uiState) {
                        is MainUiState.Loading -> {

                        }

                        is MainUiState.Success -> {
                            mainRecyclerViewAdapter.submitList(uiState.data.contents)
                        }

                        is MainUiState.Error -> {

                        }
                    }
                }
        }
    }

    private fun initTodoListRecyclerView() {
        mainListAdapter = MainListAdapter()
        binding.rvMainList.adapter = mainListAdapter
    }
}