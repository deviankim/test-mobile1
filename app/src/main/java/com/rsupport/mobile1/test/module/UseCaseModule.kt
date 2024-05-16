package com.rsupport.mobile1.test.module

import com.rsupport.mobile1.test.remote.UseCase
import com.rsupport.mobile1.test.remote.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {
    @Provides
    @ViewModelScoped
    fun provideTestUseCase(repository: Repository) = UseCase(repository)
}