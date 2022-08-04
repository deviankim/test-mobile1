package com.rsupport.mobile1.test.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rsupport.mobile1.test.data.GettyData
import com.rsupport.mobile1.test.data.GettyList
import com.rsupport.mobile1.test.utils.Utils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup

class MainViewModel  :  ViewModel(){

    var gettyLiveData : MutableLiveData<GettyList> = MutableLiveData()
    var loadingLiveData : MutableLiveData<Boolean> = MutableLiveData()

    fun gettingImage() {
        CoroutineScope(Dispatchers.IO).launch {
            loadingLiveData.postValue(true)
            runCatching {
                val doc = Jsoup.connect(Utils.DOMAIN).get()
                val arrayList = ArrayList<GettyData>()
                doc.select(Utils.GETTY_IMAGE).forEach {
                    arrayList.add(GettyData(it.attr("alt"),it.attr("src")))
                }
                gettyLiveData.postValue(GettyList(arrayList))
                loadingLiveData.postValue(false)
            }
        }
    }
}