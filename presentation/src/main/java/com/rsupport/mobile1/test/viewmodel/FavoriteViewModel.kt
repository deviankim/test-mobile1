package com.rsupport.mobile1.test.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blue.domain.model.PhotoData
import com.blue.domain.usecase.favorite.AddFavoriteUseCase
import com.blue.domain.usecase.favorite.DeleteFavoriteUseCase
import com.blue.domain.usecase.favorite.GetFavoriteUseCase
import com.blue.domain.usecase.photo.GetPhotoUseCase
import com.rsupport.mobile1.test.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoriteUseCase: GetFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase
): ViewModel() {

    val favoriteUIState: StateFlow<UIState> = getFavoriteUseCase().map {
        if(it.isEmpty())
            UIState.Error(
                mainMassage = "저장한 이미지가 없어요.",
                subMassage = "Home 화면에서 이미지의 하트를 눌러봐요!"
            )
        else
            UIState.Success(
                data = it
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

    fun deleteFavorite(id: Int){
        viewModelScope.launch {
            deleteFavoriteUseCase(id)
        }
    }
}