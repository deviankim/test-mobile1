package com.rsupport.mobile1.test.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blue.domain.usecase.favorite.DeleteFavoriteUseCase
import com.blue.domain.usecase.favorite.GetFavoriteUseCase
import com.rsupport.mobile1.test.state.FavoriteUIState
import com.rsupport.mobile1.test.state.HomeUIState
import dagger.hilt.android.lifecycle.HiltViewModel
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

    val favoriteHomeUIState: StateFlow<FavoriteUIState> = getFavoriteUseCase().map {
        if(it.isEmpty())
            FavoriteUIState.Error(
                mainMassage = "저장한 이미지가 없어요.",
                subMassage = "Home 화면에서 이미지의 하트를 눌러보세요!"
            )
        else
            FavoriteUIState.Success(
                data = it
            )
    }.catch {
        FavoriteUIState.Error(
            mainMassage = it.message ?: "Error",
            subMassage = ""
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = FavoriteUIState.Loading
    )

    fun deleteFavorite(id: Int){
        viewModelScope.launch {
            deleteFavoriteUseCase(id)
        }
    }
}