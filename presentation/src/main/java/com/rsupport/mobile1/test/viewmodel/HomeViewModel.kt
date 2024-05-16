package com.rsupport.mobile1.test.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blue.domain.model.PhotoData
import com.blue.domain.state.ResourceState
import com.blue.domain.usecase.favorite.AddFavoriteUseCase
import com.blue.domain.usecase.favorite.DeleteFavoriteUseCase
import com.blue.domain.usecase.favorite.GetFavoriteIdUseCase
import com.blue.domain.usecase.photo.GetPhotoUseCase
import com.blue.domain.util.Failure
import com.rsupport.mobile1.test.state.HomeUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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

    private val _photoData: MutableSharedFlow<ResourceState<List<PhotoData>>> = MutableSharedFlow()
    val photoData: SharedFlow<ResourceState<List<PhotoData>>> = _photoData

    val homeUIState: StateFlow<HomeUIState> =
        getFavoriteIdUseCase().combine(photoData) { idList, photoList ->
            when(photoList){
                is ResourceState.Success -> {
                    HomeUIState.Success(data = photoList.data.map {
                        it.copy(favorite = idList.contains(it.photoId)) }
                    )
                }
                is ResourceState.Error-> {
                    HomeUIState.Error(
                        mainMassage = photoList.failure.message,
                    )
                }
                is ResourceState.Loading -> {
                    HomeUIState.Loading
                }
            }
        }.catch {
            HomeUIState.Error(
                mainMassage = it.message ?: "Error",
                subMassage = ""
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = HomeUIState.Loading
        )

    fun getPhotoData() {
        viewModelScope.launch {
            getPhotoUseCase().onEach {
                _photoData.emit(it)
            }.catch {
                _photoData.emit(ResourceState.Error(Failure.NetworkConnection))
            }.launchIn(viewModelScope)
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