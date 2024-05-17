package com.rsupport.mobile1.test.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blue.domain.usecase.favorite.DeleteFavoriteUseCase
import com.blue.domain.usecase.favorite.GetFavoriteUseCase
import com.blue.domain.util.Constants.ERROR_MAIN_EMPTY_LIST
import com.blue.domain.util.Constants.ERROR_SUB_EMPTY_LIST
import com.blue.domain.util.Constants.ERROR_UNHANDLED
import com.rsupport.mobile1.test.state.UIState
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

    /**
     * "좋아요"를 누른 Photo List를 통해 Compose에서 사용할 UI State를 생산합니다.
     * Photo List가 비었을 경우, 예외가 발생한 경우 Error Type을 반환합니다.
     */
    val favoriteHomeUIState: StateFlow<UIState> = getFavoriteUseCase().map {
        if(it.isEmpty())
            UIState.Error(ERROR_MAIN_EMPTY_LIST, ERROR_SUB_EMPTY_LIST)
        else
            UIState.Success(data = it)
    }.catch {
        UIState.Error(ERROR_UNHANDLED)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = UIState.Loading
    )

    /**
     * Photo Id를 통해 내부 저장소에 존재하는 Photo를 삭제하는 함수입니다.
     *
     * @param "좋아요"를 누른 Photo ID를 받습니다.
     */
    fun deleteFavorite(id: Int){
        viewModelScope.launch {
            deleteFavoriteUseCase(id)
        }
    }
}