package com.rsupport.mobile1.test.ui.main

import androidx.fragment.app.viewModels
import com.rsupport.mobile1.test.R
import com.rsupport.mobile1.test.comm.BaseFragment
import com.rsupport.mobile1.test.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.fragment_main
    override val viewModel: MainViewModel by viewModels()

    override fun initView() {

    }
}