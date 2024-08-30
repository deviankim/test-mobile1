package com.rsupport.mobile1.test.domain.repository

interface GettyRepository{
    suspend fun getImageUrls(phrase: String): List<String>
}