package com.rsupport.mobile1.test.viewmodel

import android.graphics.Insets.add
import android.util.Log
import androidx.lifecycle.*
import com.bumptech.glide.load.engine.Resource
import com.rsupport.mobile1.test.data.GettyImage
import com.rsupport.mobile1.test.domain.GetGettyImageUseCase
import com.rsupport.mobile1.test.network.Uistate
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import java.util.function.BinaryOperator
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

@HiltViewModel
class GettyImageViewModel @Inject constructor(
    private val getGettyImageUseCase: GetGettyImageUseCase
) : ViewModel() {

    private val errorMessage = MutableLiveData<String>()

    private val _gettyImages = MutableLiveData<List<GettyImage>?>()
    val gettyImages: LiveData<List<GettyImage>?>
        get() = _gettyImages

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading


    fun getImages(page: Int) {
        viewModelScope.launch {
            val data = getGettyImageUseCase.getGettyImages(page).asLiveData()
        }
    }
}