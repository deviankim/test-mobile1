package com.rsupport.mobile1.test.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rsupport.mobile1.test.R
import com.rsupport.mobile1.test.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        setLayout()
        observerData()
    }

    private fun setLayout() {
        var page = 1
        viewModel.gettingImage("image",page,applicationContext)

        binding.listView.apply {
            setTag(R.string.view_tag,R.layout.getty_item)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    (layoutManager as LinearLayoutManager).let { manager ->
                        val visibleItemCount: Int = manager.childCount
                        val totalItemCount: Int = manager.itemCount
                        val pastVisibleItems: Int = manager.findLastVisibleItemPosition()

                       if (pastVisibleItems + visibleItemCount >= totalItemCount && !viewModel.loadingLiveData.value!!) {
                           viewModel.gettingImage("image",++page,applicationContext)
                           return
                        }
                    }
                }
            })
        }
    }

    private fun observerData(){
       viewModel.loadingLiveData.observe(this){
           if (it){
               binding.progressBar.show()
           }else{
               binding.progressBar.hide()
           }
           Log.d("kjs-loading",it.toString())
       }

       viewModel.gettyLiveData.observe(this){
            binding.model = it
       }
    }
}