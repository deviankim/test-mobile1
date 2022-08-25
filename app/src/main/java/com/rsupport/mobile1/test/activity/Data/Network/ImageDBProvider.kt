package com.rsupport.mobile1.test.activity.Data.Network

import android.util.Log
import com.rsupport.mobile1.test.activity.Data.Entity.ImageEntity
import com.rsupport.mobile1.test.activity.Data.Entity.ImagesEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import javax.inject.Singleton

object ImageDBProvider{
    private val list = mutableListOf<ImageEntity>()
    suspend fun getAllImages(): List<ImageEntity> = withContext(Dispatchers.IO) {
        var doc = Jsoup.connect("https://www.gettyimages.com/photos/collaboration").get()
        var element = doc.select("img.MosaicAsset-module__thumb___yvFP5")
        element.forEach { imgTag ->
            var url = imgTag.attr("src")
            list.add(ImageEntity(url = url))
        }
        list
    }
}

