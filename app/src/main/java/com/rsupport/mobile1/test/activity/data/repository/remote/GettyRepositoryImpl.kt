package com.rsupport.mobile1.test.activity.data.repository.remote

import com.rsupport.mobile1.test.activity.data.mapper.toUiGettyModel
import com.rsupport.mobile1.test.activity.domain.model.UiGettyModel
import com.rsupport.mobile1.test.activity.domain.repository.GettyRepository
import javax.inject.Inject


class GettyRepositoryImpl @Inject constructor(private val gettyRemoteDataSource: GettyRemoteDataSource): GettyRepository {
    override suspend fun getGettyItem(url: String): Result<List<UiGettyModel>>{
        return Result.success(gettyRemoteDataSource.getGettyItem(url).toUiGettyModel())
    }
}