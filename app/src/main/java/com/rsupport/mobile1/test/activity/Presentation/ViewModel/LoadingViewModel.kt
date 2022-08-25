package com.rsupport.mobile1.test.activity.Presentation.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rsupport.mobile1.test.activity.Data.Entity.ImageEntity
import com.rsupport.mobile1.test.activity.Data.Repository.ImageRepository
import com.rsupport.mobile1.test.activity.Presentation.BaseFile.BaseViewModel
import com.rsupport.mobile1.test.activity.Presentation.State.ImageState
import com.rsupport.mobile1.test.activity.Presentation.State.LoadingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoadingViewModel @Inject constructor(
    private val imageRepository: ImageRepository
) : BaseViewModel() {
    private val _ImageLiveData = MutableLiveData<LoadingState>(LoadingState.UnInitialized)
    val ImageLiveData = _ImageLiveData
    override fun fetchData(): Job = viewModelScope.launch{
        _ImageLiveData.postValue(LoadingState.Loading)
        _ImageLiveData.postValue(LoadingState.Success(imageRepository.getAllImageFromWeb()))
    }

    fun saveImageDataToLocalDB(imageEntity: ImageEntity) = viewModelScope.launch{
        imageRepository.saveImageToLocalDB(imageEntity)
    }
}