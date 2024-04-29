package com.rsupport.mobile1.test.activity.domain.repository

import com.rsupport.mobile1.test.activity.domain.model.UiGettyModel

interface GettyRepository{
    suspend fun getGettyItem(url: String): Result<List<UiGettyModel>>
}