package com.rsupport.mobile1.test.activity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.HttpStatusException
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor()  : ViewModel() {
    private val TAG = MainViewModel::class.java.simpleName

    private val _imgUrlData = MutableLiveData<List<String>>()
    val imgUrlData: LiveData<List<String>> get() = _imgUrlData

    fun fetchIcons(searchWord: String) {
        viewModelScope.launch {
            try {
                val iconUrls = getIconUrls(searchWord)
                _imgUrlData.value = iconUrls
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private suspend fun getIconUrls(searchWord: String): List<String> =
        withContext(Dispatchers.IO) {
            val iconUrls = mutableListOf<String>()
            try {
                val url = "https://www.gettyimages.com/photos/$searchWord"
                val userAgent =
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36"

                val doc: Document = Jsoup.connect(url).userAgent(userAgent).get()
                val elements: Elements = doc.select("img")

                for (element in elements) {
                    val imgUrl = element.attr("src")
                    if (imgUrl.isNotEmpty() && imgUrl.startsWith("http")) {
                        iconUrls.add(imgUrl)
                    }
                }
            } catch (e: HttpStatusException) {
                Log.e(TAG, "HTTP error", e)
                e.printStackTrace()
            } catch (e: Exception) {
                Log.e(TAG, "Other Error", e)
                e.printStackTrace()
            }
            iconUrls
        }
}
