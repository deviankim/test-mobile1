package com.rsupport.mobile1.test

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rsupport.mobile1.test.adapter.ImgAdapter
import com.rsupport.mobile1.test.databinding.ActivityMainBinding
import com.rsupport.mobile1.test.item.RowImgSrc
import org.jsoup.HttpStatusException
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

class MainActivity : AppCompatActivity() {
    private val rowImgList:ArrayList<RowImgSrc?> = ArrayList<RowImgSrc?>()
    private val imgAdapter: ImgAdapter = ImgAdapter(rowImgList)
    private var isLoading:Boolean = true
    private var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.apply {
            binding.recyclerView.layoutManager = LinearLayoutManager(context)
            binding.recyclerView.adapter = imgAdapter
            binding.recyclerView.addOnScrollListener(scrollListener)
        }
        GetImgSrc().execute(rowImgList.size)
    }

    private val scrollListener = object:RecyclerView.OnScrollListener(){
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager: LinearLayoutManager = recyclerView.layoutManager as LinearLayoutManager;
            if(!isLoading && layoutManager != null && layoutManager.findLastCompletelyVisibleItemPosition() == rowImgList.size - 1){
                recyclerView.scrollToPosition(rowImgList.size - 1)
                GetImgSrc().execute(rowImgList.size)
                isLoading = true
            }
        }
    }



    @SuppressLint("StaticFieldLeak")
    inner class GetImgSrc:AsyncTask<Int, Void, Int>(){
        private val WEB_URL: String = "https://www.gettyimages.com/photos/collaboration?assettype=image&sort=mostpopular&phrase=collaboration&license=rf,rm&page=$page"

        override fun onPreExecute() {
            rowImgList.add(null)
            imgAdapter.notifyItemInserted(rowImgList.size - 1)
        }

        override fun doInBackground(vararg itemCnt: Int?): Int? {
            try {
                val doc:Document = Jsoup.connect(
                    String.format(WEB_URL, if (itemCnt[0]!! <= 1) 1 else itemCnt[0]!! / 20 + 1)
                ).get()

                val imgElementList:Elements = doc
                    .select("img[class=\"MosaicAsset-module__thumb___yvFP5\"]")

                for (i in 0 until imgElementList.size step 3){
                    rowImgList.add(
                        RowImgSrc(
                            imgElementList.get(i).attr("src"),
                            imgElementList.get(i+1).attr("src"),
                            imgElementList.get(i+2).attr("src")
                        )
                    )
                }
            }catch (httpStatusException : HttpStatusException){
                httpStatusException.printStackTrace()
            }catch (exception :Exception){
                exception.printStackTrace()
            }
            return itemCnt[0]
        }

        @SuppressLint("NotifyDataSetChanged")
        override fun onPostExecute(result: Int) {
            isLoading = false
            rowImgList.removeAt(result)
            imgAdapter.notifyDataSetChanged()
            if(page == 100){
                Toast.makeText(applicationContext, "last page!", Toast.LENGTH_SHORT).show()
            }else page++
        }
    }
}