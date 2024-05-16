package com.rsupport.mobile1.test.viewModel.home

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.rsupport.mobile1.test.model.GettyItem
import com.rsupport.mobile1.test.service.GettyService
import com.rsupport.mobile1.test.ui.home.HomeAdapter
import com.rsupport.mobile1.test.viewModel.DisposableViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jsoup.Jsoup

class HomeViewModel(private val api: GettyService) : DisposableViewModel() {
    private lateinit var homeAdapter: HomeAdapter

    private lateinit var listGetty: MutableList<GettyItem>

    fun loadData(listContainer: RecyclerView) {
        addDisposable(api.getGettyList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                if (response.isSuccessful) {
                    response?.let {
                        val doc = Jsoup.parseBodyFragment(response.body()!!.string())
                        val figure = doc.select("figure img")
                        val size = figure.size

                        listGetty = arrayListOf()

                        for (i in 0 until size) {
                            val src = figure[i].attr("src")
                            val alt = figure[i].attr("alt")
                            listGetty.add(GettyItem(img = src, contents = alt))
                        }

                        homeAdapter = HomeAdapter(listGetty)
                        listContainer.adapter = homeAdapter
                    }
                }
            }, { error ->
                Log.d("error", "ERROR => ${error?.message}")
            })
        )
    }
}