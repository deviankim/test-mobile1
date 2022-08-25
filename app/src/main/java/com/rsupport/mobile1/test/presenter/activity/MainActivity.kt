package com.rsupport.mobile1.test.presenter.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.rsupport.mobile1.test.databinding.ActivityMainBinding
import com.rsupport.mobile1.test.presenter.activity.ui_state.MainUiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG = this::class.java.simpleName

    private val viewModel by viewModels<MainViewModel>()
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        renderUiState()
    }

    private fun renderUiState() = lifecycleScope.launchWhenStarted {
        Log.d(TAG, "renderUiState() called")
        viewModel.mainState.collect {
            Log.w(TAG, "renderUiState: ${it::class.java.simpleName}")
            when (it) {
                is MainUiState.None -> viewModel.callGettyImage()
                is MainUiState.Success -> {
                    binding.iconLoading.visibility = View.GONE
                    Log.i(TAG, "Data size: ${it.uiDataList.size}")
                    Toast.makeText(this@MainActivity, "Data size: ${it.uiDataList.size}", Toast.LENGTH_SHORT).show()
                }
                is MainUiState.Fail -> {
                    binding.iconLoading.visibility = View.GONE
                    Log.e(TAG, "renderUiState: ${it.e.message}", it.e)
                    Toast.makeText(this@MainActivity, "Fail: ${it.e.message}", Toast.LENGTH_SHORT).show()
                }
                is MainUiState.Loading -> {
                    binding.iconLoading.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}