package com.blue.domain.repo


interface PhotoRepo {
    suspend fun getPhotoData()
}