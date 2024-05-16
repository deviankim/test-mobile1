package com.rsupport.mobile1.test.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blue.domain.model.PhotoData
import com.blue.domain.usecase.favorite.AddFavoriteUseCase
import com.blue.domain.usecase.favorite.DeleteFavoriteUseCase
import com.blue.domain.usecase.favorite.GetFavoriteIdUseCase
import com.blue.domain.usecase.favorite.GetFavoriteUseCase
import com.blue.domain.usecase.photo.GetPhotoUseCase
import com.rsupport.mobile1.test.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPhotoUseCase: GetPhotoUseCase,
    private val getFavoriteIdUseCase: GetFavoriteIdUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase
): ViewModel() {

    private val _photoData: MutableStateFlow<List<PhotoData>> = MutableStateFlow(emptyList())
    val photoData: StateFlow<List<PhotoData>> = _photoData

//    val homeUIState: StateFlow<UIState> = getPhotoUseCase().map {
//        UIState.Success(data = it)
//    }.catch {
//        UIState.Error(it.message ?: "Error")
//    }.stateIn(
//        scope = viewModelScope,
//        started = SharingStarted.WhileSubscribed(5_000),
//        initialValue = UIState.Loading
//    )

    val homeUIState: StateFlow<UIState> = getFavoriteIdUseCase().combine(photoData){ idList, photoList ->
        UIState.Success(
            data = photoList.map { it.copy(favorite = idList.contains(it.photoId)) }
        )
    }.catch {
        UIState.Error(
            mainMassage = it.message ?: "Error",
            subMassage = ""
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = UIState.Loading
    )

    fun getPhotoData(){
        viewModelScope.launch {
            _photoData.emit(getPhotoUseCase())
        }
    }

    fun addFavorite(data: PhotoData){
        viewModelScope.launch {
            addFavoriteUseCase(data)
        }
    }

    fun deleteFavorite(id: Int){
        viewModelScope.launch {
            deleteFavoriteUseCase(id)
        }
    }
}