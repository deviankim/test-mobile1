package com.kimwijin.presentation.ui.image

import com.kimwijin.common.log.TestAppLogger
import com.kimwijin.domain.base.Result
import com.kimwijin.domain.usecase.GetImageUseCase
import com.kimwijin.presentation.base.BaseViewModel
import com.kimwijin.presentation.base.DispatcherProvider
import com.kimwijin.presentation.base.onMain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

/**
 * 메인 화면의 UI 데이터를 관리하기 위한 클래스 입니다.
 * 이 클래스는 화면 상태 변경 시 데이터의 보존을 보장하고, UI 클래스와 분리하여 UI 비즈니스 로직을 처리 합니다.
 *
 * @author (김위진)
 * @since (2024-05-21)
 */
@HiltViewModel
class ImageListViewModel @Inject constructor(
    dispatcherProvider: DispatcherProvider,
    private val getImageUseCase: GetImageUseCase,
) : BaseViewModel(dispatcherProvider) {

    private val _state = MutableStateFlow<ImageListViewState>(ImageListViewState.Empty)
    val state: StateFlow<ImageListViewState> = _state

    fun getImages(page: String) {
        onMain {
            _state.value = ImageListViewState.Loading
            getImageUseCase.invoke(page)
                .catch { caues ->
                    _state.value = ImageListViewState.Error(caues.message)
                    TestAppLogger.d("kwj ERROR = $caues")
                }
                .collectLatest { result ->
                    when (result) {
                        is Result.Success -> {
                            if (result.value.isNotEmpty()) {
                                _state.value = ImageListViewState.GetImages(result.value)
                            } else {
                                _state.value = ImageListViewState.Empty
                            }
                        }

                        is Result.Failure -> {
                            _state.value = ImageListViewState.Error(result.message)
                        }

                        is Result.Exception -> {
                            _state.value = ImageListViewState.Error(result.throwable?.message)
                        }
                    }
                }
        }
    }
}