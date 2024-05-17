package com.rsupport.mobile1.test.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blue.domain.model.PhotoData
import com.blue.domain.state.ResourceState
import com.blue.domain.usecase.favorite.AddFavoriteUseCase
import com.blue.domain.usecase.favorite.DeleteFavoriteUseCase
import com.blue.domain.usecase.favorite.GetFavoriteIdUseCase
import com.blue.domain.usecase.photo.GetPhotoUseCase
import com.blue.domain.util.Constants
import com.blue.domain.util.Failure
import com.rsupport.mobile1.test.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
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

    /**
     * 네트워크 통신을 통해 얻은 Photo List와 내부 저장소를 통해 얻은 "좋아요"를 누른 Photo ID List를 결합하여,
     * Compose에서 사용할 UI State를 생산합니다
     */
    val homeUIState: StateFlow<UIState> =
        getFavoriteIdUseCase().combine(photoData) { idList, photoList ->
            when(photoList){
                is ResourceState.Success -> {
                    UIState.Success(data = photoList.data.map {
                        it.copy(favorite = idList.contains(it.photoId)) }
                    )
                }
                is ResourceState.Error-> {
                    UIState.Error(
                        mainMassage = photoList.failure.message,
                    )
                }
                is ResourceState.Loading -> {
                    UIState.Loading
                }
            }
        }.catch {
            UIState.Error(mainMassage = Constants.ERROR_UNHANDLED,)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UIState.Loading
        )

    /**
     * 네트워크 통신을 통해 Photo List를 제공받아 _photoData에 전달하는 함수입니다.
     */
    fun getPhotoData() {
        viewModelScope.launch {
            getPhotoUseCase().onEach {
                _photoData.emit(it)
            }.catch {
                _photoData.emit(ResourceState.Error(Failure.NetworkConnection))
            }.launchIn(viewModelScope)
        }
    }

    /**
     * "좋아요"를 누른 Photo Data를 내부 저장소에 저장하는 함수입니다.
     *
     * @param "좋아요"를 누른 Photo를 받습니다.
     */
    fun addFavorite(data: PhotoData) {
        viewModelScope.launch {
            addFavoriteUseCase(data)
        }
    }

    /**
     * Photo Id를 통해 내부 저장소에 존재하는 Photo 를 삭제하는 함수입니다.
     *
     * @param "좋아요"를 누른 Photo ID를 받습니다.
     */
    fun deleteFavorite(id: Int) {
        viewModelScope.launch {
            deleteFavoriteUseCase(id)
        }
    }
}