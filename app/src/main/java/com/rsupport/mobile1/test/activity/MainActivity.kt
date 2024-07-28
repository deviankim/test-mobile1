package com.rsupport.mobile1.test.activity

import android.os.Bundle
import android.view.View
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import com.rsupport.mobile1.test.R
import okhttp3.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gridView: GridView = findViewById(R.id.gridView)
        val textView: AppCompatTextView = findViewById(R.id.textView)

        fetchImages { images ->
            runOnUiThread {
                textView.visibility = View.GONE
                gridView.visibility = View.VISIBLE
                val adapter = ImageAdapter(this, images)
                gridView.adapter = adapter
            }
        }
    }

    private fun fetchImages(callback: (List<String>) -> Unit) {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://www.gettyimages.com/photos/collaboration")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.let { body ->
                    val doc: Document = Jsoup.parse(body.string())
                    val imageElements: Elements = doc.select("img[src]")
                    val imageUrls = imageElements.map { it.attr("src") }.take(20)
                    callback(imageUrls)
                }
            }
        })
    }
}