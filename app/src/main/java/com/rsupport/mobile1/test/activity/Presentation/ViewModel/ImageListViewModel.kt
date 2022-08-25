package com.rsupport.mobile1.test.activity.Presentation.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rsupport.mobile1.test.activity.Data.Entity.ImageEntity
import com.rsupport.mobile1.test.activity.Data.Repository.ImageRepository
import com.rsupport.mobile1.test.activity.Presentation.BaseFile.BaseViewModel
import com.rsupport.mobile1.test.activity.Presentation.State.ImageState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageListViewModel @Inject constructor(
    private val imageRepository : ImageRepository
) : BaseViewModel(){
    private val _ImageLiveData = MutableLiveData<ImageState>(ImageState.UnInitialized)
    val ImageLiveData = _ImageLiveData
    override fun fetchData(): Job = viewModelScope.launch{
        _ImageLiveData.postValue(ImageState.Loading)
        _ImageLiveData.postValue(ImageState.Success(imageRepository.getAllImageFromLocalDB()))
    }

}