package com.rsupport.mobile1.test.presenter.activity

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rsupport.mobile1.test.databinding.ActivityMainBinding
import com.rsupport.mobile1.test.presenter.activity.ui_state.MainUiState
import com.rsupport.mobile1.test.presenter.adapter.MainAdapter
import com.rsupport.mobile1.test.util.CustomDecoration
import com.rsupport.mobile1.test.util.toDp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG = this::class.java.simpleName

    private val viewModel by viewModels<MainViewModel>()
    private val mainAdapter by lazy { MainAdapter() }


    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnMainError.setOnClickListener {
            viewModel.callGettyDatabase()
        }

        setRecyclerView()
        renderUiState()
    }

    private fun setRecyclerView() {
        binding.recyclerView.apply {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(context)

            addItemDecoration(CustomDecoration(height = 1.toDp(),
                paddingLeft = 8.toDp(),
                paddingRight = 8.toDp(),
                color = Color.parseColor("#FFEFEFEF")))

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)

                    val lastItemPosition = (this@apply.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                    val itemTotalCount = this@apply.adapter?.let { it.itemCount - 1 } ?: 0


                    if (lastItemPosition + 5 > itemTotalCount && newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                        if (viewModel.mainState.value is MainUiState.Success) {
                            viewModel.callGettyDatabase()
                        }
                    }
                }
            })
        }
    }

    private fun renderUiState() = lifecycleScope.launchWhenStarted {
        Log.d(TAG, "renderUiState() called")
        viewModel.mainState.collectLatest {
            Log.w(TAG, "renderUiState: ${it::class.java.simpleName}")

            stateByVisible(it)

            when (it) {
                is MainUiState.None -> viewModel.callGettyDatabase()
                is MainUiState.Success -> {
                    if (it.uiDataList.isNotEmpty()) {
                        mainAdapter.submitList(it.uiDataList)
                    }
                    Log.d(TAG, "renderUiState: Success --> Size[${it.uiDataList.size}]")
                }
                is MainUiState.Fail -> {
                    it.e.printStackTrace()
                    Log.e(TAG, "renderUiState: Fail", it.e)
                }
                is MainUiState.Loading -> {
                    // Handle loading state..
                }
            }

        }
    }

    private fun stateByVisible(state: MainUiState) {
        binding.tvMainEmpty.isVisible = if (state is MainUiState.Success) state.uiDataList.isEmpty() else false
        binding.iconLoading.isVisible = state is MainUiState.Loading
        binding.errorGroup.isVisible = state is MainUiState.Fail
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}