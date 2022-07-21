package com.rsupport.mobile1.test.activity.di

import com.rsupport.mobile1.test.activity.domain.GettyImageRepository
import com.rsupport.mobile1.test.activity.domain.GettyImageRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindGettyImageRepository(gettyImageRepository: GettyImageRepositoryImpl) : GettyImageRepository
}