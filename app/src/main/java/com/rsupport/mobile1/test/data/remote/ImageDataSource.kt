package com.example.crawlin_test.data.remote

import com.example.crawlin_test.data.model.ImageData

interface ImageDataSource {

    fun getImg(): List<ImageData>
}