package com.rsupport.mobile1.test.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.rsupport.mobile1.test.databinding.ActivityMainBinding
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jsoup.Jsoup
import org.jsoup.select.Elements

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.listView.layoutManager = GridLayoutManager(this, 3)
        doTask("https://www.gettyimages.com/photos/collaboration")
    }

    private fun doTask(url: String) {
        var docTitle = ""
        var itemArr: ArrayList<GettyItem> = arrayListOf()

        Single.fromCallable {
            try {
                val doc = Jsoup.connect(url).get()
                val elements: Elements =
                    doc.select("[data-testid=gallery-items-container] img[src]")
                run elemLoop@{
                    elements.forEachIndexed { index, elem ->
                        var title = elem.attr("alt")
                        var src = elem.attr("src")
                        var item = GettyItem(title, src)
                        itemArr.add(item)
                    }
                }
                docTitle = doc.title()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return@fromCallable docTitle   // subscribe 호출
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                // documentTitle 응답 성공 시
                { text ->
                    binding.listView.adapter = GettyAdapter(itemArr)
                },
                // documentTitle 응답 오류 시
                {
                    it.printStackTrace()
                })
    }
}