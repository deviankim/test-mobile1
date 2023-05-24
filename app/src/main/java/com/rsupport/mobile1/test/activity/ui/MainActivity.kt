package com.rsupport.mobile1.test.activity.ui

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.rsupport.mobile1.test.activity.data.vo.ImageData
import com.rsupport.mobile1.test.activity.viewmodel.MainViewModel
import com.rsupport.mobile1.test.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    private val viewModel: MainViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        with(viewModel) {
            viewModel.getImageData()

            resultHtml.observe(this@MainActivity) {
                val tmpList = ArrayList<ImageData>()
                Html.fromHtml(it, Html.FROM_HTML_MODE_LEGACY, Html.ImageGetter { source ->
                    Log.e("TEST", "source : $source")
                    if (source != null && source.contains("https")) {
                        tmpList.add(ImageData(index, source))
                        _imageList.value = tmpList
                        index = index++
                    }
                    null
                }, null)
            }
        }

        with(binding) {
            rcViewImage.setItemViewCacheSize(50)
            rcViewImage.layoutManager = StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL)
        }
    }
}