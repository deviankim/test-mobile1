package com.rsupport.mobile1.test.domain

import android.util.Log
import coil.network.HttpException
import com.rsupport.mobile1.test.data.database.GettyDao
import com.rsupport.mobile1.test.domain.data.GettyDomainData
import com.rsupport.mobile1.test.domain.data.toEntity
import com.rsupport.mobile1.test.presenter.activity.ui_state.MainUiData
import com.rsupport.mobile1.test.presenter.activity.ui_state.MainUiState
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.jsoup.select.Elements
import javax.inject.Inject

@ViewModelScoped
class GetGettyInfoUseCase @Inject constructor(
    private val gettyDao: GettyDao,
    private val mapper: GettyMapper,
) {

    private val TAG = this::class.java.simpleName

    companion object {
        private const val ATTR_CONTENT = "content"
        private const val META_DATA_AUTHOR = "meta[itemprop=author]"
        private const val META_DATA_DATE = "meta[itemprop=uploadDate]"
        private const val META_DATA_THUMBNAIL_URL = "meta[itemprop=thumbnailUrl]"
    }

    suspend operator fun invoke(crawlingElements: Elements): Flow<MainUiState> {
        Log.d(TAG, "invoke() called")
        return flow {
            var mainUiState: MainUiState = MainUiState.Loading

            emit(mainUiState)

            try {
                val crawlingData = crawlingElements
                    .map {
                        // Element 에서 필요한 div 데이터 찾기
                        it.getElementsByClass("gallery-asset-schema")
                    }.map { element ->
                        // Domain set
                        val domain = GettyDomainData(
                            author = element.select(META_DATA_AUTHOR).attr(ATTR_CONTENT),
                            date = element.select(META_DATA_DATE).attr(ATTR_CONTENT),
                            thumbnailUrl = element.select(META_DATA_THUMBNAIL_URL).attr(ATTR_CONTENT)
                        )
                        // Database insert
                        gettyDao.insertGettyData(domain.toEntity())
                        domain
                    }

                // 성공
                mainUiState = MainUiState.Success(crawlingData.map {
                    MainUiData(
                        author = it.author,
                        date = it.date,
                        thumbnailUrl = it.thumbnailUrl
                    )
                })

            } catch (httpException: HttpException) {
                mainUiState = MainUiState.Fail(httpException)
            } catch (e: Exception) {
                mainUiState = MainUiState.Fail(e)
            }
            emit(mainUiState)
        }.flowOn(Dispatchers.Main)
    }
}