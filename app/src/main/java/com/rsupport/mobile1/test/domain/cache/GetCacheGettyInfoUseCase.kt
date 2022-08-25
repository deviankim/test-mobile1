package com.rsupport.mobile1.test.domain.cache

import android.util.Log
import com.rsupport.mobile1.test.data.database.GettyDao
import com.rsupport.mobile1.test.domain.GettyMapper
import com.rsupport.mobile1.test.presenter.activity.ui_state.MainUiData
import com.rsupport.mobile1.test.presenter.activity.ui_state.MainUiState
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.jvm.Throws

@ViewModelScoped
class GetCacheGettyInfoUseCase @Inject constructor(
    private val gettyDao: GettyDao,
    private val mapper: GettyMapper,
) {

    private val TAG = this::class.java.simpleName

    @Throws(Exception::class)
    suspend fun invoke(): Flow<MainUiState> {
        Log.d(TAG, "invoke() called")
        return flow {

            val cacheData = gettyDao.getGettyImage().map {
                mapper.map(it)
            }

            val state = MainUiState.Success(
                cacheData.map {
                    MainUiData(
                        author = it.author,
                        date = it.date,
                        thumbnailUrl = it.thumbnailUrl
                    )
                }
            )

            emit(state)
        }.flowOn(Dispatchers.Main)
    }
}