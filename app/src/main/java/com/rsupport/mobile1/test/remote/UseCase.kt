package com.rsupport.mobile1.test.remote

import javax.inject.Inject

class UseCase @Inject constructor(private val repository: Repository) {
    suspend fun getGettyList() = repository.getGettyList()
}