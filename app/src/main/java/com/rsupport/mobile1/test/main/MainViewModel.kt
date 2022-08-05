package com.rsupport.mobile1.test.main

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rsupport.mobile1.test.data.GettyData
import com.rsupport.mobile1.test.data.GettyList
import com.rsupport.mobile1.test.utils.Utils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import org.jsoup.Connection
import org.jsoup.Jsoup

class MainViewModel  :  ViewModel(){

    var gettyLiveData : MutableLiveData<GettyList> = MutableLiveData()
    var loadingLiveData : MutableLiveData<Boolean> = MutableLiveData()

    fun gettingImage(type: String,page : Int,context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            loadingLiveData.postValue(true)

            runCatching {
                val response = Jsoup.connect(Utils.DOMAIN.plus("assettype=${type}&page=${page}"))
                    .method(Connection.Method.GET)
                    .execute()

                when(response.statusCode()){
                    Utils.SUCCESS -> {
                        val defaultList = ArrayList<GettyData>()
                        response.parse().select(Utils.GETTY_IMAGE).forEach { element ->
                            if (gettyLiveData.value == null){
                                defaultList.add(GettyData(element.attr("alt"),element.attr("src")))
                            }else{
                                gettyLiveData.value?.list?.add(GettyData(element.attr("alt"),element.attr("src")))
                            }
                        }.run {
                            gettyLiveData.postValue(if (defaultList.size > 1) GettyList(defaultList) else gettyLiveData.value)
                        }

                        loadingLiveData.postValue(false)

                    } else -> {
                        Toast.makeText(context,"네트워크 통신 오류",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}