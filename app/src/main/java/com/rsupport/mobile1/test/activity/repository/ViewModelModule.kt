package com.rsupport.mobile1.test.activity.repository

import com.rsupport.mobile1.test.activity.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * ViewModel 모듈화
 */
val viewmodel = module{
    viewModel { MainViewModel(get()) }
}