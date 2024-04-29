package com.rsupport.mobile1.test.activity.domain.usecase.main

import com.rsupport.mobile1.test.activity.domain.model.UiGettyModel
import com.rsupport.mobile1.test.activity.domain.repository.GettyRepository
import javax.inject.Inject

class GetGettyItemUseCase @Inject constructor(
    private val gettyRepository: GettyRepository
){
   suspend operator fun invoke(
       url: String
   ) : Result<List<UiGettyModel>>{
       return gettyRepository.getGettyItem(url)
   }
}