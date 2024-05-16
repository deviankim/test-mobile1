package com.rsupport.mobile1.test.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blue.domain.model.PhotoData
import com.blue.domain.usecase.favorite.AddFavoriteUseCase
import com.blue.domain.usecase.favorite.DeleteFavoriteUseCase
import com.blue.domain.usecase.favorite.GetFavoriteIdUseCase
import com.blue.domain.usecase.photo.GetPhotoUseCase
import com.rsupport.mobile1.test.state.HomeUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPhotoUseCase: GetPhotoUseCase,
    private val getFavoriteIdUseCase: GetFavoriteIdUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase
) : ViewModel() {

    private val _loadingState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loadingState: StateFlow<Boolean> = _loadingState

    private val _photoData: MutableSharedFlow<List<PhotoData>> = MutableSharedFlow()
    val photoData: SharedFlow<List<PhotoData>> = _photoData

    val homeUIState: StateFlow<HomeUIState> =
        getFavoriteIdUseCase().combine(photoData) { idList, photoList ->
            if (photoList.isEmpty())
                HomeUIState.Loading()
            else
                HomeUIState.Success(data = photoList.map { it.copy(favorite = idList.contains(it.photoId)) },)
        }.catch {
            HomeUIState.Error(
                mainMassage = it.message ?: "Error",
                subMassage = ""
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = HomeUIState.Loading()
        )

    fun getPhotoData() {
        viewModelScope.launch {
            _loadingState.value = true
            _photoData.emit(getPhotoUseCase())
            _loadingState.value = false
        }
    }

    fun addFavorite(data: PhotoData) {
        viewModelScope.launch {
            addFavoriteUseCase(data)
        }
    }

    fun deleteFavorite(id: Int) {
        viewModelScope.launch {
            deleteFavoriteUseCase(id)
        }
    }
}