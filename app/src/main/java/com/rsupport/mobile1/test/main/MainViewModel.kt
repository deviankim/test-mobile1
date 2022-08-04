package com.rsupport.mobile1.test.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rsupport.mobile1.test.data.GettyData
import com.rsupport.mobile1.test.network.Network
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup

class MainViewModel  :  ViewModel(){

    var gettyLiveData : MutableLiveData<ArrayList<GettyData>> = MutableLiveData()

    fun gettingImage() {
        CoroutineScope(Dispatchers.IO).launch {
            runCatching {
                val doc = Jsoup.connect(Network.DOMAIN).get()
                val arrayList = ArrayList<GettyData>()
                doc.select("div.GalleryItems-module__searchContent___DbMmK article a figure picture img").forEach {
                    arrayList.add(GettyData(it.attr("alt"),it.attr("src")))
                }
                gettyLiveData.postValue(arrayList)
            }
        }
    }
}