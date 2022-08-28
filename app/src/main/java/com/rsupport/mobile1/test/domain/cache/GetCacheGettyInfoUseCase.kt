package com.rsupport.mobile1.test.domain.cache

import android.util.Log
import coil.network.HttpException
import com.rsupport.mobile1.test.data.database.GettyDao
import com.rsupport.mobile1.test.domain.GetGettyInfoUseCase
import com.rsupport.mobile1.test.domain.GettyMapper
import com.rsupport.mobile1.test.presenter.activity.ui_state.MainUiData
import com.rsupport.mobile1.test.presenter.activity.ui_state.MainUiState
import com.rsupport.mobile1.test.util.Constants
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import javax.inject.Inject

@ViewModelScoped
class GetCacheGettyInfoUseCase @Inject constructor(
    private val gettyDao: GettyDao,
    private val mapper: GettyMapper,
    private val getGettyInfoUseCase: GetGettyInfoUseCase,
) {

    private val TAG = this::class.java.simpleName

    @Throws(Exception::class)
    suspend operator fun invoke(prevPage: Int, page: Int): Flow<MainUiState> {
        Log.d(TAG, "invoke() called --> [prevPage: $prevPage, page: $page]")
        return flow {
            emit(MainUiState.Loading)
            try {
                val cacheData = gettyDao.getGettyImage().map { mapper.map(it) }

                if (cacheData.isEmpty() || prevPage < page) {
                    // 2. Database 가 null 일 경우, retrofit 조회
                    withContext(Dispatchers.IO) {
                        val elements = Jsoup.connect(String.format(Constants.WEB_URL, page)).get()
                            .select("div.gallery-asset-schema div")

                        return@withContext getGettyInfoUseCase.invoke(elements)
                    }.collect {
                        if (it is MainUiState.Success) {
                            val uiData = cacheData.map { domain ->
                                MainUiData(
                                    author = domain.author,
                                    date = domain.date,
                                    thumbnailUrl = domain.thumbnailUrl
                                )
                            }.toMutableList()
                            uiData.addAll(it.uiDataList.map { data -> data.copy() })
                            emit(MainUiState.Success(uiData.toList()))
                        } else {
                            emit(it)
                        }
                    }
                } else {
                    emit(MainUiState.Success(cacheData.map {
                        MainUiData(
                            author = it.author,
                            date = it.date,
                            thumbnailUrl = it.thumbnailUrl
                        )
                    }))
                }
            } catch (httpException: HttpException) {
                emit(MainUiState.Fail(httpException))
            } catch (e: Exception) {
                emit(MainUiState.Fail(e))
            }
        }.flowOn(Dispatchers.Main)
    }
}