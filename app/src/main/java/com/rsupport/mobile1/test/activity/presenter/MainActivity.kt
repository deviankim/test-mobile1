package com.rsupport.mobile1.test.activity.presenter

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.rsupport.mobile1.test.R
import com.rsupport.mobile1.test.activity.common.ProgressDialog
import com.rsupport.mobile1.test.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "GETTY_IMAGE"
    }

    private val viewModel: MainViewModel by viewModels()
    private val gettyImageAdapter = GettyImageAdapter()

    private val loadingDialog: ProgressDialog? by lazy {
        ProgressDialog(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel

        binding.rvGettyImage.adapter = gettyImageAdapter
        binding.rvGettyImage.layoutManager = LinearLayoutManager(this)

        viewModel.gettyImageList.observe(this) { list ->
            list?.let(gettyImageAdapter::submitList)
        }

        viewModel.progress.observe(this) { event ->
            event?.getContentIfNotHandled()?.let { progress ->
                if (progress) {
                    loadingDialog?.show()
                } else {
                    loadingDialog?.dismiss()
                }
            }
        }

        viewModel.errorToast.observe(this) { event ->
            event?.getContentIfNotHandled()?.let { progress ->
                if (progress) {
                    Toast.makeText(this, getString(R.string.main_network_error), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}