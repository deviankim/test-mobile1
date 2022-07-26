package com.rsupport.mobile1.test.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.*
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rsupport.mobile1.test.R
import com.rsupport.mobile1.test.activity.model.TestModel
import org.jsoup.Jsoup
import org.w3c.dom.Document
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    val testmodel: ArrayList<TestModel> = arrayListOf()
    var count: Int? = 0
    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        handler = Handler()
        CountCheck()

    }
//---------------------------------더하기 빼기-------------------------------------
    private fun CountCheck() {
        val edt = findViewById<TextView>(R.id.count_edt)
        val minus_btn = findViewById<Button>(R.id.minus_btn)
        val plus_btn = findViewById<Button>(R.id.plus_btn)
        plus_btn.setOnClickListener {
            showProgress(true)
            if (edt.text.toString().toInt() < 100) {
                count = count?.plus(1)
                edt.text = count.toString()
                PutData()
            } else {
                testmodel.clear()
                Toast.makeText(this, "마지막 페이지입니다.", Toast.LENGTH_SHORT).show()
            }
        }
        minus_btn.setOnClickListener {
            showProgress(true)
            if (edt.text.toString().toInt() > 0) {
                count = count?.minus(1)
                edt.text = count.toString()
                PutData()
            } else {
                Toast.makeText(this, "마지막 페이지입니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
    //---------------------------------더하기 빼기-------------------------------------


    //---------------------------------데이터 넣기-------------------------------------
    private fun PutData() {
        val recycler_view = findViewById<RecyclerView>(R.id.recycler_view)
        thread(start = true) {
            val ab =
                "https://www.gettyimages.com/photos/collaboration?assettype=image&phrase=collaboration&sort=mostpopular&license=rf%2Crm&page="
            val doc = Jsoup.connect(ab + count.toString()).get()
            val content =
                doc.select("div.Gallery-module__container___eT6yU div div article a figure picture img")
            testmodel.clear()
            for (e in content) {
                val url = e.absUrl("src")
                val model = TestModel(url.toString())
                this.testmodel.add(model)
            }
            handler.post {
                val recyclerView = recycler_view
                recyclerView.layoutManager = GridLayoutManager(this, 3)

                recyclerView.itemAnimator = DefaultItemAnimator()
                recyclerView.adapter = RecyclerViewAdapter(testmodel)
                val adapter = recyclerView.adapter
                adapter?.notifyDataSetChanged()

                showProgress(false)
            }
        }
    }
    //---------------------------------데이터 넣기-------------------------------------

    inner class RecyclerViewAdapter(var testmodel: ArrayList<TestModel>) :
        RecyclerView.Adapter<CustomViewHolder>() {

        override fun getItemCount(): Int {
            return testmodel.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
            val view: View =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_test, parent, false)
            return CustomViewHolder(view)
        }

        override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
            Glide.with(holder.itemView.context)
                .load(testmodel[position].testImg)
                .override(300, 300)
                .into(holder.item_test_img)
        }
    }

    inner class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val item_test_img: ImageView = view.findViewById(R.id.item_test_img)
    }

    fun showProgress(isShow: Boolean) {
        val progress = findViewById<ProgressBar>(R.id.progress)
        if (isShow) progress.visibility = View.VISIBLE
        else progress.visibility = View.GONE
    }


}