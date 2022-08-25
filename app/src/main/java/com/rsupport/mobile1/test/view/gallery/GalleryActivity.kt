package com.rsupport.mobile1.test.view.gallery

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.rsupport.mobile1.test.R
import com.rsupport.mobile1.test.databinding.ActivityMainBinding
import com.rsupport.mobile1.test.util.LogUtil
import com.rsupport.mobile1.test.view.decoration.GalleryDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryActivity : AppCompatActivity() {

    private val viewModel: GalleryViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        LogUtil.d("")
        super.onCreate(savedInstanceState)
        val mainAdapter = MainAdapter()
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            it.lifecycleOwner = this
            it.viewModel = viewModel
            it.galleryRv.run {
                setHasFixedSize(true)
                addItemDecoration(GalleryDecoration(
                    GalleryViewModel.SPAN_COUNT,
                    resources.getDimension(R.dimen.gallery_space).toInt()
                ))
                layoutManager = GridLayoutManager(this@GalleryActivity, GalleryViewModel.SPAN_COUNT)
                adapter = mainAdapter
            }
            setContentView(it.root)
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.getPhotosCollaboration()
    }
}
