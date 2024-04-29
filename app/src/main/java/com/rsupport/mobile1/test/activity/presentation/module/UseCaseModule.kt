package com.rsupport.mobile1.test.activity.presentation.module

import com.rsupport.mobile1.test.activity.domain.repository.GettyRepository
import com.rsupport.mobile1.test.activity.domain.usecase.main.GetGettyItemUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideGetGettyUseCase(gettyRepository: GettyRepository) =
        GetGettyItemUseCase(gettyRepository)
}
