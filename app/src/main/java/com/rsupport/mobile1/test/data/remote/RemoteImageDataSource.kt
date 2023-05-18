package com.example.crawlin_test.data.remote

import android.util.Log
import com.example.crawlin_test.data.model.ImageData
import org.jsoup.HttpStatusException
import org.jsoup.Jsoup
import javax.inject.Inject

class RemoteImageDataSource @Inject constructor() : ImageDataSource{

    override fun getImg(): List<ImageData> {
        val ImgList = mutableListOf<ImageData>()

        try {
            val url = "https://www.gettyimages.com/photos/collaboration"
            val document = Jsoup.connect(url).get()
            val listDoc = document.select("div.zF13TZBfnku4pwItorUU div.vItTTzk8rQvUIXjdVfi4 picture img")

            repeat(listDoc.size) {
                listDoc[it].also { element ->
                    val src: String = listDoc.get(it).attr("src")

                    ImgList.add(
                        ImageData(
                            id = src.replace("https://media.gettyimages.com/id/", ""),
                            imgUrl = src
                        )
                    )
                }
            }

        }catch (e2 : HttpStatusException){
            e2.printStackTrace()
        }

        catch (e: Exception){
            e.printStackTrace()
            Log.e("RemoteImageDataSource","error")
        }

        return ImgList.toList()
    }


}