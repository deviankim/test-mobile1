package com.rsupport.mobile1.test.activity

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.rsupport.mobile1.test.R
import com.rsupport.mobile1.test.activity.adapter.ImageAdapter
import com.rsupport.mobile1.test.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val TAG: String = MainActivity::class.java.simpleName

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val adapter = ImageAdapter(mutableListOf())
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        // viewModel init
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        // adapter init
        val gridLayoutManager = GridLayoutManager(this, 3)
        binding.recyclerImageList.layoutManager = gridLayoutManager
        binding.recyclerImageList.adapter = adapter

        setObservers()
        searchImage("collaboration")
    }

    private fun setObservers() {
        mainViewModel.imgUrlData.observe(this) { imgUrlData ->
            adapter.updateData(imgUrlData)
        }

        binding.editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // 텍스트가 변경되기 전에 수행할 작업
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // 텍스트가 변경되는 중에 수행할 작업
            }

            @RequiresApi(Build.VERSION_CODES.M)
            override fun afterTextChanged(s: Editable?) {
                // 텍스트가 변경된 후에 수행할 작업
                s?.let {
                    if(it.isNotEmpty()) {
                        binding.btnSearch.setBackgroundColor(getColor(R.color.flamingo))
                        binding.btnSearch.setTextColor(getColor(R.color.white))
                    } else {
                        binding.btnSearch.setBackgroundColor(getColor(R.color.alto))
                        binding.btnSearch.setTextColor(getColor(R.color.dusty_gray))
                    }
                }
            }
        })
    }

    fun requestImage(view: View) {
        val query = binding.editTextSearch.text.toString().trim()
        searchImage(query)
    }

    private fun searchImage(query: String) {
        Log.d(TAG, "searchImage: $query")
        mainViewModel.fetchIcons(query)
    }
}
