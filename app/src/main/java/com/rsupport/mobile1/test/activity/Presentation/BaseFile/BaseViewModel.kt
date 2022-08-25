package com.rsupport.mobile1.test.activity.Presentation.BaseFile

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job

abstract class BaseViewModel : ViewModel() {
    abstract fun fetchData() : Job
}