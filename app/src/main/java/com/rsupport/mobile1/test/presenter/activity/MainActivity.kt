package com.rsupport.mobile1.test.presenter.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.rsupport.mobile1.test.R
import com.rsupport.mobile1.test.presenter.activity.data.MainUiData
import com.rsupport.mobile1.test.util.Constants.WEB_URL
import dagger.hilt.android.AndroidEntryPoint
import org.jsoup.Jsoup

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG = this::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Thread() {
            try {
                // 크롤링 진행
                val doc = Jsoup.connect(String.format(WEB_URL, 1)).get()
                val contents = doc
                    .select("div.gallery-asset-schema div")
                    .map {
                        it.getElementsByClass("gallery-asset-schema").first()?.let { element ->
                            MainUiData(
                                author = element.select("meta[itemprop=author]").attr("content"),
                                date = element.select("meta[itemprop=uploadDate]").attr("content"),
                                thumbnailUrl = element.select("meta[itemprop=thumbnailUrl]").attr("content")
                            )
                        }
                    }


                Log.d(TAG, "onCreate() called\n ${contents.size}\n${contents.first()}")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.start()


    }
}