package com.rsupport.mobile1.test.activity.repository

import org.koin.dsl.module

/**
 * Repository 모듈화
 */
val repositoryModule = module{
    single<ImageListRepository>{
        ImageRepositoryImpl()
    }
}