package com.rsupport.mobile1.test.network

import android.util.Log
import com.rsupport.mobile1.test.network.Network
import com.rsupport.mobile1.test.network.NetworkApi
import org.jsoup.Jsoup

class NetworkService (private val networkApi: NetworkApi){

    fun post(){
        var doc = Jsoup.connect(Network.DOMAIN).get()

        Log.d("kjs-doc",doc.title())
    }
}