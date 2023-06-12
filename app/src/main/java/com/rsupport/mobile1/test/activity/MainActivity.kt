package com.rsupport.mobile1.test.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.rsupport.mobile1.test.BR
import com.rsupport.mobile1.test.R
import com.rsupport.mobile1.test.activity.adapter.ImageAdapter
import com.rsupport.mobile1.test.activity.viewmodel.MainViewModel
import com.rsupport.mobile1.test.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity: BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val layoutId : Int = R.layout.activity_main
    override val variable : Int = BR.viewModel
    override val viewModel: MainViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 이미지 리스트 데이터가 가져와졌을 때에
        // 해당 데이터를 리스트에 뿌려주고 뷰를 그려준다.
        viewModel.mImageList.observe(this@MainActivity, { imgList ->
            mViewBinding?.imageRecyclerview?.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                setHasFixedSize(true)
                adapter = ImageAdapter(imgList)
            }
        })
    }
}