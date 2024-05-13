package com.rsupport.mobile1.test.core.repository

interface ImageRepository {
    suspend fun getImage(page: Int): List<String>
}