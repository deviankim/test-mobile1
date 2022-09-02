package com.rsupport.mobile1.test.viewmodel

import androidx.lifecycle.*
import androidx.paging.PagingData
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
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.function.BinaryOperator
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

@HiltViewModel
class GettyImageViewModel @Inject constructor(
    private val getGettyImageUseCase: GetGettyImageUseCase
) : ViewModel() {

    private val _uiStateLiveData = MutableLiveData<Uistate>()
    val uiStateLiveData: LiveData<Uistate>
        get() = _uiStateLiveData

    private val _gettyImages = MutableLiveData<List<GettyImage>>()
    val gettyImages: LiveData<List<GettyImage>?>
        get() = _gettyImages

    fun getImages(page: Int) {
        viewModelScope.launch {
            //로딩 시작 - view에서 옵저빙
            _uiStateLiveData.postValue(Uistate.Loading)
            try {
                val data = getGettyImageUseCase.getGettyImages(page).toList()
                _uiStateLiveData.postValue(Uistate.Success(data))
            } catch (e: Exception) {
                _uiStateLiveData.postValue(Uistate.Fail(e))
            }
        }
    }
}