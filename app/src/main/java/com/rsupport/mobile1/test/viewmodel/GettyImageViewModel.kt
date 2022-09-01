package com.rsupport.mobile1.test.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.load.engine.Resource
import com.rsupport.mobile1.test.data.GettyImage
import com.rsupport.mobile1.test.domain.GetGettyImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GettyImageViewModel @Inject constructor(
    private val getGettyImageUseCase: GetGettyImageUseCase) : ViewModel(){

    private val _gettyImages = MutableLiveData<Resource<MutableList<GettyImage>>>()
    val gettyImages: LiveData<Resource<MutableList<GettyImage>>> = _gettyImages

    fun getImages(page: Int) {
     viewModelScope.launch {
         getGettyImageUseCase.getGettyImages(page).collect {
         }
     }
    }
}