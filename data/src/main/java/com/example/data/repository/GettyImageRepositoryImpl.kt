package com.example.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.data.common.Dispatcher
import com.example.data.common.RSupportDispatchers
import com.example.data.paging.GettyImagePagingSource
import com.example.data.remote.source.GettyImagesRemoteDataSource
import com.example.domain.entity.GettyImage
import com.example.domain.repository.GettyImageRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GettyImageRepositoryImpl @Inject constructor(
    private val gettyImagesRemoteDataSource: GettyImagesRemoteDataSource,
    @Dispatcher(RSupportDispatchers.IO) private val dispatcher: CoroutineDispatcher
) : GettyImageRepository {

    override fun getGettyImages(): Flow<PagingData<GettyImage>> = Pager(
        config = PagingConfig(
            initialLoadSize = PAGE_SIZE,
            pageSize = PAGE_SIZE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            GettyImagePagingSource { page ->
                gettyImagesRemoteDataSource.getGettyImages(page)
            }
        }
    )
        .flow
        .map { pagingData ->
            pagingData.map {
                it.toEntity()
            }
        }
        .flowOn(dispatcher)

    companion object {
        private const val PAGE_SIZE = 60
    }
}
